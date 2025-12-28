import { getProducts } from "@/utils/actions";
import ProductElement from "./ProductElement";

export default async function ProductList() {

  const products = await getProducts()

  return (
    <div>
      {
        products.map(item => (
          <div key={item.id.toString()}>
            <ProductElement product={item} />
          </div>
        ))
      }
    </div>
  )
}
