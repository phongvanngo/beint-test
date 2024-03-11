import axios, { AxiosInstance, AxiosRequestConfig } from 'axios';

const baseURL = 'http://localhost:8080/api'; 

const axiosClient: AxiosInstance = axios.create({
  baseURL,
  timeout: 5000, 
  headers: {
    'Content-Type': 'application/json',
    
  },
});

export default axiosClient;