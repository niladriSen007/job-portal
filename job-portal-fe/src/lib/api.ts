import axios from "axios";
import { env } from "./env";

export const api = axios.create({
  baseURL: env.backendApiUrl,
  headers: { "Content-Type": "application/json" },
  withCredentials: true, // This is for sending cookies with requests, if your backend requires authentication
});

let tokenGetter: (() => Promise<string | null>) | null = null;
export const tokenSetter = (getter: () => Promise<string | null>) => {
  tokenGetter = getter;
};

axios.interceptors.request.use(
  async (config) => {
    // You can add any custom logic here, like adding an authorization token
    // For example:
    if (tokenGetter) {
      const token = await tokenGetter();
      if (token) {
        config.headers = config.headers || {};
        config.headers["Authorization"] = `Bearer ${token}`;
      }
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  },
);
