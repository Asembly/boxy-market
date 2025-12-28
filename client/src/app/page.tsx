import CreateProduct from "@/components/features/CreateProduct";
import ProductList from "@/components/widget/ProductList";
import UserList from "@/components/widget/UserList";
import { createProduct, getProductPhotoUrlById, getProducts, getUserById, getUsers } from "@/utils/actions";
import { User } from "@/utils/types";

export default async function Home() {

  return (
    <div>

      <UserList />
      <CreateProduct />
      <ProductList />

    </div >
  );
}
