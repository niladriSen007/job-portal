import { post } from "@/lib/apiMethods";
import { useAuthStore } from "@/store/auth/auth-store";
import type { LoginRequest, UserResponse } from "@/store/auth/auth-types";
import { useMutation } from "@tanstack/react-query";

export function useLogin() {
  const { setUser, setError, setLoading } = useAuthStore();

  const loginMutation = useMutation({
    mutationFn: async (loginData: LoginRequest) => {
      setLoading();
      const response = await post<typeof loginData, UserResponse>(
        "/auth/login",
        loginData,
      );
      if (response.status === "error" || response.statusCode !== 200) {
        throw new Error(response.errors?.[0]?.message || "Unknown error");
      }
      return response.data;
    },
    onSuccess: (data) => {
      setUser(data?.userResponse || null);
    },
    onError: (error: Error) => {
      setError(error.message);
    },
  });

  return { loginMutation };
}
