import type { NextConfig } from 'next'
import path from 'path'
import createNextIntlPlugin from 'next-intl/plugin'

const nextConfig: NextConfig = {
  /* config options here */
  turbopack: {
    root: path.resolve(__dirname, './')
  }
}
const withNextIntl = createNextIntlPlugin()
export default withNextIntl(nextConfig)
