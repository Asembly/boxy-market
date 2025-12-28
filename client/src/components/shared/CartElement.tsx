import { getProductById } from "@/utils/actions";
import { Cart, Product } from "@/utils/types";
import ProductPhoto from "../features/ProductPhoto";

export default async function CartElement(props: { product_id: String }) {

  const product = await getProductById(props.product_id)

  return (
    <div>
      <div>
        title: {product.title}
      </div>
      <div>
        description: {product.description}
      </div>
      <div>
        price: {product.price}
      </div>
      <div>
        sale: {product.sale}
      </div>
      <div>
        <ProductPhoto product_id={product.id} />
      </div>
    </div>
  )
}
