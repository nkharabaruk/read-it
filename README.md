When using MySQL, it's important to create DB with UTF-8 encoding as default. Please use the following line:

<code>CREATE DATABASE readit DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_unicode_ci;</code>

To start application simply execute this inside <code>/rest</code>: 

<code>gradle bootRun</code>