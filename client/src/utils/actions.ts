import axios from "axios"
import { serverInstance } from "./config"
import { Product, User } from "./types"

export async function getUsersById(id: string) {

  const users: User[] = await serverInstance.get(`/user-service/get?id=${id}`)
    .then(res => res.data)
    .catch(error => error)

  console.log(users + "Пользователи")

  if (users.length == null)
    return []

  return users
}

export async function getProductsByUserId(user_id: string) {

  console.log("START")

  const products: Product[] = await axios.get(`http://localhost:9000/product-service/get/user?user_id=${user_id}`)
    .then(res => res.data)
    .catch(error => error)

  console.log(products + "Продукты по user_id")

  if (products.length == null)
    return []

  return products
}
