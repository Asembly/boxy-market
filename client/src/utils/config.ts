import axios from "axios";

export const serverInstance = axios.create({
  baseURL: process.env.BASE_URL,
  timeout: 1000,
});
