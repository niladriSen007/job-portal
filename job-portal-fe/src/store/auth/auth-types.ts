type AuthStatus = "idle" | "loading" | "authenticated" | "unauthenticated";

export type AuthStore = {
  status: AuthStatus;
  isBootStraped: boolean;
  user: AppUser | null;
  error: string | null;
  setLoading: () => void;
  setUser: (user: AppUser | null) => void;
  setError: (errorMessage: string | null) => void;
  clearAuthState: () => void;
};

export type AppUser = {
  id: string;
  email: string;
  firstName: string;
  lastName: string;
  phoneNumber: string;
  role: "ROLE_ADMIN" | "ROLE_EMPLOYER" | "ROLE_JOB_SEEKER";
  userPermissions: Set<
    | "JOB_SEARCH"
    | "JOB_CREATE"
    | "JOB_UPDATE"
    | "JOB_DELETE"
    | "SUSPEND_USER"
    | "DELETE_USER"
    | "ACTIVATE_USER"
    | "VIEW_ALL_USERS"
  >;
  status: "ACTIVE" | "INACTIVE" | "SUSPENDED" | "DELETED";
  lastLoggedInTime: string | null;
};

export type UserResponse = {
  message: string;
  accessToken: string;
  refreshToken: string;
  userResponse: AppUser | null;
};

export type LoginRequest = {
  email: string;
  password: string;
};

export type SignupRequest = {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  phoneNumber: string;
};

export type SignupResponse = {
  message: string;
  userResponse: AppUser | null;
};
