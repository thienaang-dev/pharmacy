'use client'

import { Button } from '@/components/ui/button'
import { ROUTES } from '@/constants/routes'
import { Ambulance, HouseHeart, Menu, Pill, User, X } from 'lucide-react'
import { useTranslations } from 'next-intl'
import { usePathname, useRouter } from 'next/navigation'
import { Input } from '@/components/ui/input'
import { useState } from 'react'

export function HeaderFull() {
  const router = useRouter()
  const t = useTranslations('accessability')
  const tHeader = useTranslations('header')
  const path = usePathname().split('/')[2]

  // Navigation menu props
  const [showNavigationMenu, setShowNavigationMenu] = useState(false)

  function handleNavigateToHomePage() {
    router.push(ROUTES.home)
  }

  function handleShowNavigationMenu() {
    setShowNavigationMenu((prev) => !prev)
  }

  function handleNavigateToMedicinesPage() {
    router.push(ROUTES.medicines)
    handleShowNavigationMenu()
  }

  function handleNavigateToPharmaciesPage() {
    router.push(ROUTES.pharmacies)
    handleShowNavigationMenu()
  }

  function handleNavigateToProfilePage() {
    router.push(ROUTES.profile)
    handleShowNavigationMenu()
  }

  function handleExitNavigationMenu() {
    setShowNavigationMenu((prev) => !prev)
  }

  return (
    <div className='flex justify-center'>
      <nav className='bg-primary fixed top-0 z-50 flex w-full max-w-150 flex-col gap-4 rounded-b-xl lg:max-w-3xl'>
        <div
          className={`border-secondary flex justify-between gap-4 px-5 py-2 text-white ${showNavigationMenu && 'border-b'}`}
        >
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

          <Input />

          <Button
            variant='ghost'
            size='icon-lg'
            className='hover:bg-primary p-0 hover:text-white'
            onClick={handleShowNavigationMenu}
            aria-label={t('show.navigation_menu')}
          >
            {showNavigationMenu ? <X className='size-full' /> : <Menu className='size-full' />}
          </Button>
        </div>

        {showNavigationMenu && (
          <div className='flex flex-col items-start gap-2 px-5 pb-2 text-white'>
            <div className='border-secondary grid w-full grid-cols-[auto_1fr] gap-4 border-b pb-2'>
              <Pill className='h-9 w-9' />
              <Button
                variant='link'
                className='flex justify-start text-white'
                onClick={handleNavigateToMedicinesPage}
                aria-label={t('navigate.medicines')}
                disabled={path === 'medicines'}
                aria-disabled={path === 'medicines'}
              >
                <p>{tHeader('navigate.medicines')}</p>
              </Button>
            </div>

            <div className='border-secondary grid w-full grid-cols-[auto_1fr] gap-4 border-b pb-2'>
              <HouseHeart className='h-9 w-9' />
              <Button
                variant='link'
                className='flex justify-start text-white'
                onClick={handleNavigateToPharmaciesPage}
                aria-label={t('navigate.pharmacies')}
                disabled={path === 'pharmacies'}
                aria-disabled={path === 'pharmacies'}
              >
                <p>{tHeader('navigate.pharmacies')}</p>
              </Button>
            </div>

            <div className='border-secondary grid w-full grid-cols-[auto_1fr] gap-4 pb-2'>
              <User className='h-9 w-9' />
              <Button
                variant='link'
                className='flex justify-start text-white'
                onClick={handleNavigateToProfilePage}
                aria-label={t('navigate.profile')}
                disabled={path === 'profile'}
                aria-disabled={path === 'profile'}
              >
                <p>{tHeader('navigate.profile')}</p>
              </Button>
            </div>
          </div>
        )}
      </nav>

      {showNavigationMenu && (
        <div
          className='fixed top-0 left-0 size-full bg-black opacity-20'
          onClick={handleExitNavigationMenu}
        />
      )}
    </div>
  )
}
