export interface User {
  id: String,
  username: String,
  password: String,
  created_at: Date
};

export interface Cart {
  id: String,
  products_id: String[],
  user_id: String,
}

export interface Product {
  id: String,
  user_id: String,
  price: number,
  sale: number,
  title: String,
  description: String,
  discontinued: boolean,
  photos: String[],
  created_at: Date
}
