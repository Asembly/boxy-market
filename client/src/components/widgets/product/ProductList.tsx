'use client'
import { getProductsByUserId } from "@/utils/actions"
import ProductCard from "./ProductCard"
import { Product } from "@/utils/types"
import { useEffect, useState } from "react"

export default function ProductList() {

  const [products, setProducts] = useState<Product[]>([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')
  const [page, setPage] = useState(1)

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        setLoading(true)
        const response = await getProductsByUserId('123')
        setProducts(response)
        setError('')
      } catch (err) {
        setError('Ошибка загрузки данных')
        console.error(err)
      } finally {
        setLoading(false)
      }
    }

    fetchUsers()
  }, [page])


  return (
    <div>
      {loading && <p>Загрузка...</p>}
      {error && <p className="text-red-500">{error}</p>}
      {
        products.map(item => (
          <div>
            <ProductCard key={item.id} product={item} />
          </div>
        ))
      }
    </div>
  )
}
