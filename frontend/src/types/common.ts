export interface ResponsePayload {
  code: string;
  mess: string;
}

export interface PageData<T> extends ResponsePayload {
  data: T[];
  limit: number;
  page: number;
  totalElements: number;
}

export interface DataResponse<T> extends ResponsePayload {
  data: T;
}

