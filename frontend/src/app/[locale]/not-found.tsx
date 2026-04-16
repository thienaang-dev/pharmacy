'use client'

import { Button } from '@/components/ui/button'
import { ROUTES } from '@/constants/routes'
import { useTranslations } from 'next-intl'
import { useRouter } from 'next/navigation'

export default function NotFoundPage() {
  const t = useTranslations('not_found')
  const tAccessability = useTranslations('accessability')
  const router = useRouter()

  function handleNavigateToHomepage() {
    router.push(ROUTES.home)
  }

  return (
    <div className='flex min-h-screen items-center justify-center'>
      <div className='bg-accent flex max-w-[80%] flex-col justify-center gap-4 rounded-lg p-10 shadow sm:max-w-100'>
        <h1 className='text-2xl font-bold'>{t('title')}</h1>
        <p className='text-base'>{t('description')}</p>

        <Button
          onClick={handleNavigateToHomepage}
          className='text-base'
          aria-label={tAccessability('navigate.home')}
        >
          {t('back')}
        </Button>
      </div>
    </div>
  )
}
