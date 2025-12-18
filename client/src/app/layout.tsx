// src/app/layout.tsx
import type { Metadata } from "next";
import "./globals.css";
import { Provider } from "../components/ui/provider.tsx";
import { Container } from "@chakra-ui/react";

export const metadata: Metadata = {
  title: "Boxy Market",
  description: "Минималистичный интернет-магазин",
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="ru">
      <body>
        <Provider>
          {children}
        </Provider>
      </body>
    </html>
  );
}
