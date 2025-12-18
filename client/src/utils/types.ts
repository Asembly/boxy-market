export interface Product {
  id: string
  user_id: string
  price: number
  sale: number
  title: string
  description: string
  photos: string[]
  created_at: Date
}

export interface User {
  id: string
  username: string
  created_at: Date
}

export interface Cart {
  id: string
  user_id: string
  products_id: string[]
}
