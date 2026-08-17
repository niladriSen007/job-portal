import { useMutation } from "@tanstack/react-query";
import { useAuthStore } from "@/store/auth/auth-store";
import { post } from "@/lib/apiMethods";
import type { SignupRequest, SignupResponse } from "@/store/auth/auth-types";

export function useSignup() {
  const { setLoading, setUser, setError } = useAuthStore();
  const signupMutation = useMutation({
    mutationFn: async (signupData: SignupRequest): Promise<SignupResponse> => {
      setLoading();
      const response = await post<SignupRequest, SignupResponse>(
        "/auth/signup",
        signupData,
      );
      if (response.status === "error" || response.statusCode !== 200) {
        throw new Error(
          response.errors?.map((e) => e.message).join(", ") || "Unknown error",
        );
      }
      return response.data as SignupResponse;
    },
    onSuccess: (data: SignupResponse) => {
      setUser(data?.userResponse || null);
    },
    onError: (error: Error) => {
      setError(error.message);
    },
  });

  return { signupMutation };
}
