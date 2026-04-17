'use client'

import { useTranslations } from 'next-intl'
import { Card, CardContent, CardHeader } from '@/components/ui/card'
import { Form } from '@base-ui/react'
import { Input } from '@/components/ui/input'
import { Button } from '@/components/ui/button'
import { useForm } from 'react-hook-form'

export interface ContactUsFormData {
  name: string
  email: string
}

export function Footer() {
  const t = useTranslations('footer.contact_us')
  const { register, handleSubmit } = useForm<ContactUsFormData>()

  function handleContactUsSubmission(data: ContactUsFormData) {
    const { name, email } = data

    alert(t('not_implemented', { name, email }))
  }

  return (
    <Card className='mb-10 rounded-b-none sm:mx-auto sm:mt-5 sm:mb-0 sm:max-w-150 lg:max-w-3xl'>
      <CardHeader>
        <h2 className='text-xl'>{t('title')}</h2>
      </CardHeader>

      <CardContent>
        <Form onSubmit={handleSubmit(handleContactUsSubmission)} className='flex flex-col gap-2'>
          <Input {...register('name')} placeholder={t('name.placeholder')} required />

          <Input {...register('email')} placeholder={t('email.placeholder')} required />

          <Button>{t('submit')}</Button>
        </Form>
      </CardContent>
    </Card>
  )
}
