'use client'

import { Breakpoint, BREAKPOINTS } from '@/constants/breakpoints'
import React from 'react'

/**
 * Check if the window width is above the given breakpoint
 *
 * @param breakpoint The breakpoint to check
 * @returns `true` if the window width is above the breakpoint
 */
export function useIsAboveBreakpoint(breakpoint: Breakpoint): boolean | undefined {
  const [isAbove, setIsAbove] = React.useState<boolean | undefined>(undefined)

  React.useEffect(() => {
    function handleResize() {
      const width = window.innerWidth
      setIsAbove(width >= BREAKPOINTS[breakpoint])
    }
    handleResize()

    global.addEventListener('resize', handleResize)
    return () => global.removeEventListener('resize', handleResize)
  }, [breakpoint])

  return isAbove
}
