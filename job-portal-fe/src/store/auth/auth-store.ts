import { create } from "zustand";
import type { AuthStore } from "./auth-types";
import { devtools } from "zustand/middleware";

export const useAuthStore = create<AuthStore>()(
  devtools((set) => ({
    status: "idle",
    isBootStraped: false,
    user: null,
    error: null,
    setLoading: () => set({ status: "loading", error: null }),
    setUser: (user) =>
      set({
        user,
        status: user ? "authenticated" : "unauthenticated",
        error: null,
      }),
    setError: (errorMessage) =>
      set({
        error: errorMessage,
        isBootStraped: true,
        status: "unauthenticated",
      }),
    clearAuthState: () =>
      set({ status: "idle", user: null, error: null, isBootStraped: true }),
  })),
);
