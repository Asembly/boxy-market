import { getCartByUserId } from "@/utils/actions"
import CartElement from "./CartElement"

export default async function CartList() {

  const cart = await getCartByUserId('db4cddeb')

  return (
    <div>
      {
        cart.products_id.map(item => (
          <div>
            <CartElement product_id={item} />
          </div>
        ))
      }
    </div>
  )
}
