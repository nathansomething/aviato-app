module.exports = function(grunt) {

  // Project configuration.
  grunt.initConfig({
    bower_concat: {
  	  all: {
  	    dest: {
  	      'js': 'src/main/webapp/resources/VENDOR/_bower.js',
  	      'css': 'src/main/webapp/resources/VENDOR/_bower.css'
  	    },
  	    exclude: ['google-fonts']
      }
    },
    sass: {
  		dist: {
  			files: [{
          expand: true,
          cwd: 'src/main/webapp/resources/SCSS',
          src: ['*.scss'],
          dest: 'src/main/webapp/resources/CSS/',
  				ext: '.css'
  			}]
  		}
	   }
  });

  // Load the plugin that provides the "uglify" task.
  grunt.loadNpmTasks('grunt-bower-concat');
  grunt.loadNpmTasks('grunt-contrib-sass');

  // Default task(s).
  grunt.registerTask('default', ['bower_concat', 'sass']);

};
