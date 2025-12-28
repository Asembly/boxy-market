import { getUsers } from "@/utils/actions";
import UserElement from "./UserElement";

export default async function UserList() {

  const users = await getUsers();

  return (
    <div>
      {
        users.map(item => (
          <div key={item.id.toString()}>
            <UserElement user={item} />
          </div>
        ))
      }
    </div>
  )
}
