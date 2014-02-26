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
	        this.setUserDetails();
	        this.populateCustomers();
	        this.createCustomerBarcodesGrid();
        },
        
        populateCustomers: function() {
        	var store = new JsonRestStore({
        	    target: "/barcode/customers",
        	    syncMode: true
        	 });
        	
	        var grid = new EnhancedGrid({
	        	id: 'customersGrid',
	            store: store,
	            structure: [[
	                {
	                    name:  'Customer Id',
	                    field: 'customerId',
	                    width: '26%'
	                },
                    {
                        name:  'Customer Name',
                        field: 'name',
                        width: '37%'
                    },
                    {
                        name:  'Email',
                        field: 'email',
                        width: '37%'
                    }
	            ]]
	        });
	        
	        grid.placeAt(this.customerContainer);
	        grid.startup();
        },
        
        createCustomerBarcodesGrid: function() {
        	var store = new JsonRestStore({
        	    target: "/barcode/customerBarcodes/tescos123",
        	    syncMode: true
        	 });
        	
	        var grid = new EnhancedGrid({
	        	id: 'customerBarcodesGrid',
	            store: store,
	            structure: [[
	                 {
	                    name:  'API Key',
	                    field: 'barcodeApiKey',
	                    width: '40%'
	                },
                    {
                        name:  'Format',
                        field: 'barcodeFormat',
                        width: '26%'
                    },
                    {
                        name:  'Domain',
                        field: 'domain',
                        width: '34%'
                    }
	            ]]
	        });
	        
	        grid.placeAt(this.customerBarcodesContainer);
	        grid.startup();
        },
        
        setUserDetails: function() {
        	 var userDetailsStore = new JsonRestStore({
         	    target: "/barcode/loggedinuser/" + this.loggedinuser,
         	    syncMode: true
         	 });
 	        
 	        userDetailsStore.fetch({onComplete: dojo.hitch(this, this.gotItems)});
        },
        
        gotItems: function(response) {
        	this.loggedInUser.innerHTML = "Hello " + response.firstName + " " + response.surname + " (" + response.username + ")";
        }

	});
});