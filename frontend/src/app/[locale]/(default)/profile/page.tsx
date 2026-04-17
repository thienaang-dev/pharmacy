import { useTranslations } from 'next-intl'

export default function ProfilePage() {
  const t = useTranslations('profile')

  return (
    <div>
      <h1 className='text-2xl font-bold'>{t('title')}</h1>
    </div>
  )
}
