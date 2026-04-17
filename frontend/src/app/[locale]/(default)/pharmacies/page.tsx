import { useTranslations } from 'next-intl'

export default function PharmaciesPage() {
  const t = useTranslations('pharmacies')

  return (
    <div>
      <h1 className='text-2xl font-bold'>{t('title')}</h1>
    </div>
  )
}
