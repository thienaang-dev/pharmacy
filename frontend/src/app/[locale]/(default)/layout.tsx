import React from 'react'

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <>
      {/* TODO: Add header */}

      <main>{children}</main>

      {/* TODO: Add footer: links and legal */}
    </>
  )
}
