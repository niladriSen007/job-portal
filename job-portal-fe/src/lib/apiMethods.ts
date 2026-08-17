import type { AxiosRequestConfig } from "axios";
import { api } from "./api";
import type { ApiResponse } from "./types";
import axios from "axios";
import { env } from "@/lib/env";

function getErrorMessage(error: unknown): string {
  if (axios.isAxiosError(error)) {
    return (
      error?.response?.data?.errors[0]?.message ||
      error.message ||
      "Request failed"
    );
  }

  if (error instanceof Error) {
    return error.message;
  }
  return String(error);
}

export async function get<T>(
  url: string,
  config?: AxiosRequestConfig,
): Promise<ApiResponse<T>> {
  try {
    const response = await api.get<ApiResponse<T>>(
      `${env.coreApiPathV1}${url}`,
      config,
    );
    if (response.data.status === "error" || !response.data.data) {
      throw new Error(
        response.data.errors?.map((e) => e.message).join(", ") ||
          "Unknown error",
      );
    }
    return response.data;
  } catch (error) {
    const message = getErrorMessage(error);
    return {
      status: "error",
      statusCode: 500,
      data: null,
      errors: [{ message }],
    };
  }
}

export async function post<TRequest, TResponse>(
  url: string,
  reqBody: TRequest,
  config?: AxiosRequestConfig,
): Promise<ApiResponse<TResponse>> {
  try {
    const response = await api.post<ApiResponse<TResponse>>(
      `${env.coreApiPathV1}${url}`,
      reqBody,
      config,
    );
    if (response.data.status === "error" || !response.data.data) {
      throw new Error(
        response.data.errors?.map((e) => e.message).join(", ") ||
          "Unknown error",
      );
    }
    return response.data;
  } catch (error) {
    const message = getErrorMessage(error);
    return {
      status: "error",
      statusCode: 500,
      data: null,
      errors: [{ message }],
    };
  }
}

export async function put<TRequest, TResponse>(
  url: string,
  reqBody: TRequest,
  config?: AxiosRequestConfig,
): Promise<ApiResponse<TResponse>> {
  try {
    const response = await api.put<ApiResponse<TResponse>>(
      `${env.coreApiPathV1}${url}`,
      reqBody,
      config,
    );
    if (response.data.status === "error" || !response.data.data) {
      throw new Error(
        response.data.errors?.map((e) => e.message).join(", ") ||
          "Unknown error",
      );
    }
    return response.data;
  } catch (error) {
    const message = getErrorMessage(error);
    return {
      status: "error",
      statusCode: 500,
      data: null,
      errors: [{ message }],
    };
  }
}

export async function del<T>(
  url: string,
  config?: AxiosRequestConfig,
): Promise<ApiResponse<T>> {
  try {
    const response = await api.delete<ApiResponse<T>>(
      `${env.coreApiPathV1}${url}`,
      config,
    );
    if (response.data.status === "error" || !response.data.data) {
      throw new Error(
        response.data.errors?.map((e) => e.message).join(", ") ||
          "Unknown error",
      );
    }
    return response.data;
  } catch (error) {
    const message = getErrorMessage(error);
    return {
      status: "error",
      statusCode: 500,
      data: null,
      errors: [{ message }],
    };
  }
}

export async function patch<TRequest, TResponse>(
  url: string,
  reqBody: TRequest,
  config?: AxiosRequestConfig,
): Promise<ApiResponse<TResponse>> {
  try {
    const response = await api.patch<ApiResponse<TResponse>>(
      `${env.coreApiPathV1}${url}`,
      reqBody,
      config,
    );
    if (response.data.status === "error" || !response.data.data) {
      throw new Error(
        response.data.errors?.map((e) => e.message).join(", ") ||
          "Unknown error",
      );
    }
    return response.data;
  } catch (error) {
    const message = getErrorMessage(error);
    return {
      status: "error",
      statusCode: 500,
      data: null,
      errors: [{ message }],
    };
  }
}
