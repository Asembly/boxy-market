import { Product } from "@/utils/types";

export default function ProductCard(props: { product: Product }) {

  return (
    <div>
      Цена: {props.product.price}
    </div>
  )
}
