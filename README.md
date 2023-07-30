# GA4-snippets
Snippets of code that helps in sending events and also GA4 setup for analytics clients

Setting up Google Analytics 4 (GA4) for your client's app or website. GA4 offers powerful tracking capabilities and access to important APIs for enhanced measurement.

## Table of Contents
1. [Create a Google Analytics 4 Property](#create-a-google-analytics-4-property)
2. [Install GA4 Tracking Code](#install-ga4-tracking-code)
3. [Enable Enhanced Measurement](#enable-enhanced-measurement)
4. [Explore GA4 Important APIs](#explore-ga4-important-apis)
5. [Set Up Custom Events](#set-up-custom-events)
6. [Integrate Events in App or Website](#integrate-events-in-app-or-website)
7. [Set Up User Properties (Optional)](#set-up-user-properties-optional)
8. [Configure Data Streams and Data Import (Optional)](#configure-data-streams-and-data-import-optional)
9. [Test Event Tracking](#test-event-tracking)
10. [Review Real-Time Reports](#review-real-time-reports)
11. [Create Custom Reports and Dashboards (Optional)](#create-custom-reports-and-dashboards-optional)
12. [Set Up Data Retention and Privacy Settings](#set-up-data-retention-and-privacy-settings)
13. [Review and Optimize](#review-and-optimize)

## 1. Create a Google Analytics 4 Property

- Go to the Google Analytics website (https://analytics.google.com/) and sign in with a Google account.
- Create a new GA4 property for the client's app or website.

## 2. Install GA4 Tracking Code

- Obtain the GA4 Measurement ID (G-XXXXXXXXXX) from the GA4 property settings.
- Add the GA4 tracking code to the client's app or website's header section on all pages. The tracking code should look like this:

```html
<script async src="https://www.googletagmanager.com/gtag/js?id=G-XXXXXXXXXX"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());
  gtag('config', 'G-XXXXXXXXXX');
</script>

