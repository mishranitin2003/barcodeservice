define([
    'dojo/_base/declare', 
    'dojo/_base/lang',
    'dojo',
    'dojo/dom',
    'dojo/json',
    'dojo/dom-construct',
    'dijit/Dialog',
    'dijit/_WidgetsInTemplateMixin',
    'dojo/text!./templates/LoginDialog.html',
    'dijit/form/Button',
    'dijit/form/Form',
    'dijit/form/TextBox',
    'dojox/layout/TableContainer',
], function(declare, lang, dojo, dom, JSON, domConstruct, Dialog, _WidgetsInTemplateMixin, template){
	return declare('app.LoginDialog', [Dialog, _WidgetsInTemplateMixin], {

		title: 'Login required',
		style: 'width:400px',
		id: 'login',
		templateString: template,
		csrfParam: '',
		csrf: '',

		constructor: function(options){
			lang.mixin(this, options);
		},

		onSubmit: function(){
			var widget = this;
			var loginForm = widget.loginForm;
			var formData = "j_username=" + loginForm.value.j_username + "&j_password=" + loginForm.value.j_password + "&" + this.csrfParam + "=" + this.csrf;
			// TODO: Send encrypted password instead!!
			dojo.xhrPost({
				url: '/barcode/j_spring_security_check',
				postData: formData,
				load: function(response, ioargs){
					var res = JSON.parse(response);
					if (res.status == 'success' || res.status == 'Success') {
						widget.onSuccess(widget);
					} else {
						widget.onFailure(response);
					}
				}
			});
		},

		onSuccess: function(widget){
			window.location = "/barcode/mainscreen";
			/*var mainScreenDiv = dom.byId("mainscreen");
			require(["app/MainScreen"], function(MainScreen){
				var ms = new MainScreen();
				ms.placeAt(mainScreenDiv);
				ms.startup();
			});
			
			widget.hide();*/
		},

		onFailure: function(response){
			console.log("Failed");
		}

	});
});