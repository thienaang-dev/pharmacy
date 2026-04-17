import { Footer } from '@/components/footer/footer'
import Header from '@/components/header/header'
import React from 'react'

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <>
      <Header />
      <main className='mb-14 px-2 pt-2 sm:mt-15 sm:mb-0'>{children}</main>
      <footer>
        <Footer />
      </footer>
    </>
  )
}
