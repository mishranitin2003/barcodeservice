define([
        'dojo/_base/declare', 
        'dijit/_WidgetBase',
        'dijit/_TemplatedMixin',
        'dojo/text!./templates/MainScreen.html',
        'dijit/layout/BorderContainer',
        'dijit/layout/ContentPane',
        'dijit/layout/TabContainer',
        'dojox/grid/EnhancedGrid', 
        'dojox/data/JsonRestStore'
        ], function(declare, _WidgetBase, _TemplatedMixin, template, BorderContainer, ContentPane, TabContainer, EnhancedGrid, JsonRestStore){
	return declare('MainScreen', [_WidgetBase, _TemplatedMixin], {

        templateString: template,
        loggedinuser: "",
        
        postCreate: function() {
        	this.inherited(arguments);
        	
        	var store = new JsonRestStore({
        	    target: "/barcode/customers",
        	    syncMode: true
        	 });
        	
	        var grid = new EnhancedGrid({
	        	id: 'myGrid',
	            store: store,
	            structure: [[
	                {
	                    name:  'Customer Id',
	                    field: 'customerId',
	                    width: '25%'
	                },
                    {
                        name:  'Customer Name',
                        field: 'name',
                        width: '50%'
                    },
                    {
                        name:  'Email',
                        field: 'email',
                        width: '50%'
                    }
	            ]]
	        });
	        
	        grid.placeAt(this.customerContainer);
	        grid.startup();
	        
	        var userDetailsStore = new JsonRestStore({
        	    target: "/barcode/loggedinuser/" + this.loggedinuser,
        	    syncMode: true
        	 });
	        
	        userDetailsStore.fetch({onComplete: this.gotItems});
	        this.loggedInUser.innerHTML = "Hello nmishra";
        },
        
        gotItems: function(response) {
        	console.log(response);
        }

	});
});