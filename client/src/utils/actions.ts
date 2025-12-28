'use server'
import api from "./config";
import { Cart, Product, User } from "./types";

export async function getUsers() {
  const response: User[] = await api.get(`/user-service/`)
    .catch(e => e)
    .then(res => res.data)

  return response
}

export async function getUserById(user_id: String) {
  const response: User = await api.get(`/user-service/get?id=${user_id}`)
    .catch(e => e)
    .then(res => res.data)

  return response
}

export async function createProduct(formData: FormData) {
  const images = formData.get("images") as File
  const title = formData.get("title") as string
  const user_id = formData.get("user_id") as string
  const description = formData.get("description") as string
  const price = formData.get("price") as string

  const data_test = new FormData()
  data_test.append('images', images)

  const data = {
    user_id: user_id,
    title: title,
    description: description,
    price: price
  }

  data_test.append('data', new Blob([JSON.stringify(data)],
    {
      type: 'application/json'
    })
  )

  const response = await api.post(`/product-service/`, data_test)
    .catch(e => e)
    .then(res => res.data)

  console.log(response)
}

export async function signUp() {

}

export async function getUserByUsername(username: String) {

}

export async function getProducts() {
  const response: Product[] = await api.get(`/product-service/`)
    .catch(e => e)
    .then(res => res.data)

  return response
}

export async function getProductById(product_id: String) {

}

export async function getProductPhotoUrlById(product_id: String) {
  const response: [] = await api.get(`/product-service/get/photos?id=${product_id}`)
    .catch(e => e)
    .then(res => res.data)

  console.log(response)

  return response
}

export async function addProduct(user_id: String, product_id: String) {
  const response: Cart = await api.post(`/cart-service/add?user_id=${user_id}&product_id=${product_id}`)
    .catch(e => e)
    .then(res => res.data)

  console.log(response)

  return response
}

export async function getProductsByUserId(user_id: String) {

}

export async function getCarts() {

}


export async function getCartByUserId(user_id: String) {

}

export async function getPayments() {

}
