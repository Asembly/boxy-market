import { User } from "@/utils/types";

export default function UserElement(props: { user: User }) {
  return (
    <div>
      <div>
        Id: {props.user.id}
      </div>
      <div>
        Username: {props.user.username}
      </div>
    </div>
  )
}
