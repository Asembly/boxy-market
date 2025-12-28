import { getProductPhotoUrlById } from "@/utils/actions"

export default async function ProductPhoto(props: { product_id: String }) {

  const photos = await getProductPhotoUrlById(props.product_id)

  return (
    <div>
      {photos.map((item, i) => (
        <div key={i}>
          <img src={item} width={50} height={50} />
        </div>
      ))}
    </div>
  )
}
