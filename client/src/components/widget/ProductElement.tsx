import { Product } from "@/utils/types";
import ProductPhoto from "../features/ProductPhoto";
import AddToCart from "../features/AddToCart";

export default function ProductElement(props: { product: Product }) {
  return (
    <div>
      <div>
        {props.product.title}
      </div>
      <div>
        {props.product.description}
      </div>
      <div>
        <ProductPhoto product_id={props.product.id} />
      </div>
      <div>
        price: {props.product.price}
      </div>
      <div>
        <AddToCart user_id={'db4cddeb'} product_id={props.product.id} />
      </div>
    </div>
  )
}
