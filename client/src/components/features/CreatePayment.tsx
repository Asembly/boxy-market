import { createPayment } from "@/utils/actions"

export default function CreatePayment() {

  var confirm_url: String

  return (
    <div>
      <form action={createPayment} className="flex flex-col">
        <input type="value" name="value" placeholder="enter value" />
        <input type="currency" name="currency" placeholder="enter title" />
        <input type="type" name="type" placeholder="enter description" />
        <input type="return_url" name="return_url" placeholder="enter price" />
        <input type="capture" name="capture" placeholder="enter images" />
        <input type="description" name="description" placeholder="enter images" />
        <button type="submit">Отправить</button>
      </form>
    </div>
  )
}
