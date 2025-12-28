import { createProduct } from "@/utils/actions";

export default function CreateProduct() {
  return (
    <div>
      <form action={createProduct} className="flex flex-col">
        <input type="text" name="user_id" placeholder="enter user id" />
        <input type="text" name="title" placeholder="enter title" />
        <input type="text" name="description" placeholder="enter description" />
        <input type="number" name="price" placeholder="enter price" />
        <input type="file" name="images" placeholder="enter images" />
        <button type="submit">Отправить</button>
      </form>
    </div>
  )
}
