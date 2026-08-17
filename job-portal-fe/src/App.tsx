import { useEffect } from "react";
import "./App.css";
import { useLogin } from "./hooks/auth/useLogin";
import { useSignup } from "./hooks/auth/useSignup";
import { useAuthStore } from "./store/auth/auth-store";

function App() {
  const { user } = useAuthStore();

  const { loginMutation } = useLogin();
  const { signupMutation } = useSignup();

  useEffect(() => {
    // signupMutation.mutate({
    //   email: "test@1.com",
    //   password: "1",
    //   firstName: "Test",
    //   lastName: "User",
    //   phoneNumber: "1234567890",
    // });
    loginMutation.mutate({ email: "test@1.com", password: "1" });
  }, []);

  return <div>{user ? JSON.stringify(user) : "No user logged in"}</div>;
}

export default App;
