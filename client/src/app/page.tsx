import Body from "@/components/shared/Body";
import ProductList from "@/components/widgets/product/ProductList";

export default function Home() {
  return (
    <div className="p-4">
      <div className="mb-6">
        <h1 className="text-lg font-bold mb-1">Новинки</h1>
        <p className="text-[#1E1F22]/70 text-xs">
          Свежие поступления этой недели
        </p>
      </div>
      <Body />
    </div>
  );
}
