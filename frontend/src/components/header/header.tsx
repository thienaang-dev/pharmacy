'use client'

import { useIsAboveBreakpoint } from '@/hooks/use-is-above-breakpoint'
import { HeaderCompact } from './header-compact'
import { HeaderFull } from './header-full'

export default function Header() {
  const isAboveSmall = useIsAboveBreakpoint('sm')

  return isAboveSmall ? <HeaderFull /> : <HeaderCompact />
}
