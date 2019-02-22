# Vaadin Chart.js demo
[![Download](https://img.shields.io/bintray/v/moberwasserlechner/maven/vaadin-chartjs.svg)](https://bintray.com/moberwasserlechner/maven/vaadin-chartjs/_latestVersion)
[![Published on Vaadin Directory](https://img.shields.io/badge/Vaadin%20Directory-ChartJS-00b4f0.svg)](https://vaadin.com/directory/component/chartjs-add-on)
[![Travis](https://img.shields.io/travis/moberwasserlechner/vaadin-chartjs-demo/master.svg?maxAge=2592000)](https://travis-ci.org/moberwasserlechner/vaadin-chartjs-demo)
[![Stars on Vaadin Directory](https://img.shields.io/vaadin-directory/star/chartjs-add-on.svg)](https://vaadin.com/directory/component/chartjs-add-on)
[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.me/moberwasserlechner)

This is the demo project for [vaadin-chartjs](https://github.com/moberwasserlechner/vaadin-chartjs) library.

# Live Demo

* http://vaadin-demos.qqjtxeeuih.eu-central-1.elasticbeanstalk.com:5600

## Contribute

### Fix a bug or create a new feature

Please do not mix more than one issue in a feature branch. Each feature/bugfix should have its own branch and its own Pull Request (PR).

1. Create a issue and describe what you want to do at [Issue Tracker](https://github.com/moberwasserlechner/vaadin-chartjs/issues)
2. Create your feature branch (`git checkout -b feature/my-feature` or `git checkout -b bugfix/my-bugfix`)
3. Test your changes to the best of your ability.
4. Add a demo view to the demo application 
5. Commit your changes (`git commit -m 'Describe feature or bug'`)
6. Push to the branch (`git push origin feature/my-feature`)
7. Create a Github Pull Request

### Run the demo local

The demo application is based on Spring Boot. So its possible to run the Demo as Java Application right out of Eclipse, there is not servlet container needed as Spring Boot has a embedded Tomcat 8 included.

1. Open "Debug Configurations..." dialog
2. Create a new "Java Application"
3. Choose the "vaadin-chartjs-demo" project
4. Use "com.byteowls.vaadin.chartjs.demo.ChartJsDemoApplication" as Main class
5. Set `-Dprofile=dev` as VM argument. This ensures that source code panel in the demo is correctly filled while developing.
6. Browse to `http://localhost:8080/`

### Code Style

Please use the sun coding convention. Please do not use tabs at all!

## License

MIT. Please see [LICENSE](https://github.com/moberwasserlechner/vaadin-chartjs/blob/master/LICENSE).

## BYTEOWLS Software & Consulting

This plugin is powered by [BYTEOWLS Software & Consulting](https://byteowls.com).

### Commercial support and consulting

We create plugins for apps we build and share them **as it is** with the community.

I you have a feature request, need support how to use the plugin or
need a release breaking with our normal release cycle you have the possibility
to sponsor the development by paying for this custom development or support.

See the wiki page for how to request a quote. Donations are possible as well ;).
