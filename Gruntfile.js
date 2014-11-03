module.exports = function(grunt) {
	"use strict";

	// Project configuration.
	grunt.initConfig({
				pkg : grunt.file.readJSON('package.json'),
				banner : '/*! Dashboard v<%= pkg.version %>: <%= pkg.description %>'
						+ ' * Copyright <%= grunt.template.today("yyyy") %> <%= pkg.author.name %>\n'
						+ ' * Licensed under <%= _.pluck(pkg.licenses, "url").join(", ") %>\n'
						+ ' * <%= pkg.website %>' + ' */\n\n',
				bower : {
					dev : {
						dest : 'dest/path',
						options : {
							stripAffix : true
						}
					}
				}
			});

	grunt.loadNpmTasks('grunt-bower');

	// Default task.
	grunt.registerTask('default', ["bower" ]);

};
