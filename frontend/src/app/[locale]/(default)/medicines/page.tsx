import { useTranslations } from 'next-intl'

export default function MedicinesPage() {
  const t = useTranslations('medicines')

  return (
    <div>
      <h1 className='text-2xl font-bold'>{t('title')}</h1>
    </div>
  )
}
