'use client'
import { addProduct } from "@/utils/actions"

export default function AddToCart(props: {
  user_id: String,
  product_id: String
}) {

  const handler = async () => {
    await addProduct(props.user_id, props.product_id)
  }

  return (
    <div>
      <button onClick={() => handler()}>Add to cart</button>
    </div>
  )
}
