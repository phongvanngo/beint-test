import axios, { AxiosInstance } from 'axios';
import configs from '../configs';

const baseURL = configs.DOMAIN_NAME;

const axiosClient: AxiosInstance = axios.create({
  baseURL,
  timeout: 5000, 
  headers: {
    'Content-Type': 'application/json',
    
  },
});

export default axiosClient;