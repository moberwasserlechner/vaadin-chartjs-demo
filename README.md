# Vaadin Chart.js demo [![Download](https://img.shields.io/bintray/v/moberwasserlechner/maven/vaadin-chartjs.svg)](https://bintray.com/moberwasserlechner/maven/vaadin-chartjs/_latestVersion) [![Travis](https://img.shields.io/travis/moberwasserlechner/vaadin-chartjs/develop.svg?maxAge=2592000)](https://travis-ci.org/moberwasserlechner/vaadin-chartjs) [![Twitter Follow](https://img.shields.io/twitter/follow/michaelowl_web.svg?style=social&label=Follow&style=flat-square)](https://twitter.com/michaelowl_web)

This is the demo project for [vaadin-chartjs](https://github.com/moberwasserlechner/vaadin-chartjs) library.

# Usage

Follow me on [![Twitter Follow](https://img.shields.io/twitter/follow/michaelowl_web.svg?style=social&label=Twitter&style=flat-square)](https://twitter.com/michaelowl_web) to be notified about new releases.

## Missing something?

The Vaadin-Chartjs is only a wrapper. So if you have any feature requests or found any bugs in the javascript lib please use Chart.js's issue tracker https://github.com/chartjs/Chart.js/issues

In all other cases please create a issue at https://github.com/moberwasserlechner/vaadin-chartjs/issues or contribute to the project yourself. For contribution see the next section.

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
