'use client'

import { Button } from '@/components/ui/button'
import { ROUTES } from '@/constants/routes'
import { Ambulance, HouseHeart, Pill, User } from 'lucide-react'
import { useTranslations } from 'next-intl'
import { usePathname, useRouter } from 'next/navigation'

export function HeaderCompact() {
  const router = useRouter()
  const t = useTranslations('accessability')
  const path = usePathname().split('/')[2]

  function handleNavigateToHomePage() {
    router.push(ROUTES.home)
  }

  function handleNavigateToMedicinesPage() {
    router.push(ROUTES.medicines)
  }

  function handleNavigateToPharmaciesPage() {
    router.push(ROUTES.pharmacies)
  }

  function handleNavigateToProfilePage() {
    router.push(ROUTES.profile)
  }

  return (
    <nav className='bg-primary fixed bottom-0 z-50 w-full rounded-t-md p-2'>
      <div className='flex justify-between text-white'>
        <Button
          variant='ghost'
          size='icon-lg'
          className='hover:bg-primary p-0 hover:text-white'
          onClick={handleNavigateToHomePage}
          aria-label={t('navigate.home')}
          disabled={!path}
          aria-disabled={!path}
        >
          <Ambulance className='size-full' />
        </Button>

        <Button
          variant='ghost'
          size='icon-lg'
          className='hover:bg-primary p-0 hover:text-white'
          onClick={handleNavigateToMedicinesPage}
          aria-label={t('navigate.medicines')}
          disabled={path === 'medicines'}
          aria-disabled={path === 'medicines'}
        >
          <Pill className='size-full' />
        </Button>

        <Button
          variant='ghost'
          size='icon-lg'
          className='hover:bg-primary p-0 hover:text-white'
          onClick={handleNavigateToPharmaciesPage}
          aria-label={t('navigate.pharmacies')}
          disabled={path === 'pharmacies'}
          aria-disabled={path === 'pharmacies'}
        >
          <HouseHeart className='size-full' />
        </Button>

        <Button
          variant='ghost'
          size='icon-lg'
          className='hover:bg-primary p-0 hover:text-white'
          onClick={handleNavigateToProfilePage}
          aria-label={t('navigate.profile')}
          disabled={path === 'profile'}
          aria-disabled={path === 'profile'}
        >
          <User className='size-full' />
        </Button>
      </div>
    </nav>
  )
}
