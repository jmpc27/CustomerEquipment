Ext.onReady(function() {
	
				/* ############### Customer and Address model ############################ */
				Ext.define("Customer", {
					extend : "Ext.data.Model",
					fields : ["idCustomer", "name", "contactDetails", "contact", "idAddress", "primaryAddress", "secondaryAddress"]
				});	
				/* ##################################################################### */
				/* ############### Equipments and SLA model ############################ */
				Ext.define("Equipment", {
					extend : "Ext.data.Model",
					fields : [
						"idEquipment",
						"vendor",
						"model",
						"serialNumber",
						{
						    name: 'contractStart',
						    type: 'date',
						    dateFormat: 'Y-m-d'
						},
						{
						    name: 'contractEnd',
						    type: 'date',
						    dateFormat: 'Y-m-d'
						},
						"installationAddress",
						{
						    name: 'installationDate',
						    type: 'date',
						    dateFormat: 'Y-m-d'
						},
						"idSla",
						"sla",
						"softwareLastVersion",
						"softwareUpdate",
						"softwareVersion",
						{
						    name: 'updateDate',
						    type: 'date',
						    dateFormat: 'Y-m-d'
						},
						"idCustomer"
					],
					idProperty: 'idEquipment'
				});
				/* ##################################################################### */
				
				/* ############## Customer and Address store ########################### */				
				var customerStore = Ext.create("Ext.data.Store", {
					model : "Customer",
					autoLoad:true,
					autoSync:false,
					proxy : {
						type : "ajax",
						api : {
							create : "/CustomerEquipment-0.0.1-SNAPSHOT/customer/add",
							read : "/CustomerEquipment-0.0.1-SNAPSHOT/customer/list",
							update : "/CustomerEquipment-0.0.1-SNAPSHOT/customer/add",
							destroy : "/CustomerEquipment-0.0.1-SNAPSHOT/customer/del"
						},
						reader : {
							type : "json"
						},
						writer: {
				            type: 'json',
				            allowSingle: false
				        }
					}
				});
				/* ##################################################################### */
				
				/* ############### Equipments and SLA store ############################ */
				var equipmentStore = Ext.create('Ext.data.Store', {
			        model: 'Equipment',
			        autoLoad: true,
			        autoSync:false,
			        proxy : {
						type : "ajax",
						api : {
							create : "/CustomerEquipment-0.0.1-SNAPSHOT/customer/eqAdd",
							read : "/CustomerEquipment-0.0.1-SNAPSHOT/customer/",
							update : "/CustomerEquipment-0.0.1-SNAPSHOT/customer/eqAdd",
							destroy : "/CustomerEquipment-0.0.1-SNAPSHOT/customer/eqDel"
						},
						reader : {
							type : "json"
						},
						writer: {
				            type: 'json',
				            allowSingle: false
				        }
					}
			    });
				/* ##################################################################### */
				
				/* ############### store for field software update############################ */
				var boolStoreOpts = new Ext.data.ArrayStore({
				    fields: ['opt', 'label'],
				    data: [
				        [true, 'Sim'],
				        [false, 'Nao']
				    ]
				});
				
				/* ##################################################################### */
				Ext.create('Ext.panel.Panel', {
					renderTo: Ext.getBody(),
					width: '100%',
				    height: 600,
				    title: 'Gestao de clientes e equipmanentos',
				    layout: 'border',
				    
				    items: [{
				        // xtype: 'panel' implied by default
				        title: 'Menu',
				        region:'west',
				        xtype: 'panel',
				        margins: '5 0 0 5',
				        width: 200,
				        collapsible: true,   // make collapsible
				        id: 'west-region-container',
				        layout: {
				            type:  'vbox',
				            align: 'center'
				        },
				        defaults: {
				            margin: '10 0 0 0'
				        },
				        items:[{
				        	xtype: 'button',
				        	text: 'Sair',
				        	handler: function(){
				        		var wantToLogout = confirm('Deseja sair?');
				        		if (wantToLogout){
				        			document.getElementById("dynForm").submit();
				        		}
				        	}
				        },{
				        	xtype: 'button',
				        	text: 'Refresh',
				        	handler: function(){
				        		location.reload();
				        	}
				        }]
				    },{
				    	region: 'center',     // center region is required, no width/height specified
				    	xtype: 'gridpanel',
				        //flex: 1,
				        layout: 'fit',
				        margins: '5 5 0 0',
				        id: 'customerAddressGrid',
						store: customerStore,
						title: "Lista de clientes",
						forceFit:true,
						selType: 'cellmodel',
						plugins: [
							Ext.create('Ext.grid.plugin.CellEditing', {
								clicksToEdit: 2,
								listeners: {
									edit: function (editor, e) {
										Ext.getCmp("saveButton").enable();
									}
								}
							})],
						
							dockedItems: [{
								xtype: 'toolbar',
								items: [
//									************ button and logic to add new customer *****************************
									{
										text: 'Novo',
										tooltip: 'Adicionar novo cliente',
										iconCls: 'add',
										handler: function (btn) {
											customerStore.insert(0, {
												idCustomer: '',
												name: '',
												contactDetails: '',
												contact: '',
												idAddress: '',
												primaryAddress: '',
												secondaryAddress: ''
											});
										}

									},
									'-',
//									***********************************************************************************
//									************ button and logic to save new or existing customer ********************
									{
										id: "saveButton",
										text: 'Guardar',
										tooltip: 'Guardar as alteracoes',
										disabled: true,
										iconCls: 'save',
										handler: function (btn) {
											var wantToSave = confirm("Tem a certeza? Ira criar um novo cliente e morada");
											if (wantToSave) {
												customerStore.sync({
													success: function (batch, options) {
														alert("Registo(s) guardados na BD");
													},
													failure: function (batch, options) {
														alert("Erro a tentar guardar registo(s)");
													}
												});
											}
										}
									}]						
							}],
							
						columns:[
							{
								text:"Id",
								dataIndex:"idCustomer",
								hidden: true
							},
							{
								text:"Nome",
								dataIndex:"name",
								filter: {
									type: 'string'
								},
								editor: {
									xtype: 'textfield',
									allowBlank:false
								}
							},
							{
								text:"Email",
								dataIndex:"contactDetails",
								filter: {
									type: 'string'
								},
								editor: {
									xtype: 'textfield',
									allowBlank:false,
									regex: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
								}
							},
							{
								text:"Telefone",
								dataIndex:"contact",
								filter: {
									type: 'string'
								},
								editor: {
									xtype: 'textfield',
									allowBlank:false,
									regex: /^\d{9}$/
								}
								
							},
							{
								text:"idAddress",
								dataIndex:"idAddress",
								hidden: true					
							},
							{
								text:"Morada 1",
								dataIndex:"primaryAddress",
								width: 450,
								filter: {
									type: 'string'
								},
								editor: {
									xtype: 'textfield',
									allowBlank:false
								}
								
							},
							{
								text:"Morada 2",
								dataIndex:"secondaryAddress",
								width: 450,
								filter: {
									type: 'string'
								},
								editor: {
									xtype: 'textfield'
								}							
							},
							{
								text:"Opcoes",
								xtype: "actioncolumn",
								items:[
//									************ Button and logic to view Equipments *********************
									{
										tooltip:"Detalhes",
										iconCls:"icon-details",
										handler: function(grid,rowNum,colNum) {
											var data = customerStore.getAt(rowNum).data;
											Ext.Ajax.request({
											    url: "/CustomerEquipment-0.0.1-SNAPSHOT/customer/eqList",
											    method: 'PUT',
											    jsonData: data,
											    success: function(response) {
											    	equipmentStore.loadData(Ext.JSON.decode(response.responseText));    
											    },
											    failure: function(){
											    	console.log('Erro a carregar os dados'); 
											    }
											}); 
										}
									},
//									***************************************************************************
//									************** Button and logic to add new equipment **********************
									{
										tooltip:"Adicionar novo equipamento",
										iconCls:"icon-plus",
										handler: function(grid,rowNum,colNum) {
											var data = customerStore.getAt(rowNum).data;
											console.log(data);
											equipmentStore.insert(0, {
												idEquipment: '',
												vendor: '',
												model: '',
												serialNumber: '',
												contractStart: '',
												contractEnd: '',
												installationAddress: '',
												installationDate: '',
												idSla: '',
												sla: '',
												softwareLastVersion: '',
												softwareUpdate: false,
												softwareVersion: '',
												updateDate: '',
												idCustomer: data.idCustomer
											});
										}
									},
//									*****************************************************************************
//									************ Delete Customer button and operation *********************								
									{
										tooltip:"Eliminar",
										iconCls:"icon-delete",
										handler: function(grid,rowNum,colNum) {
											var wantToDelete = confirm("Tem a certeza? Ira eliminar o cliente e todos os equipamentos associados a ele");
											if(wantToDelete){
												customerStore.removeAt(rowNum);
												customerStore.sync({
													success: function (batch, options) {
														alert("Registo(s) eliminados da BD");
													},
													failure: function (batch, options) {
														alert("Erro a tentar eliminar registo(s)");
													}
												});
											}																				
					                    }
									}
								]
							}
						]
				    },{
				    	//renderTo: Ext.getBody(),
				    	// title: 'South Region is resizable',
				    	region: 'south',     // position for region
				        xtype: 'gridpanel',
				        height: 200,
				        split: true,         // enable resizing
				        margins: '0 5 5 5',
				        store: equipmentStore,
						id: 'equipmentSlaGrid',
						title: "Lista de equipamentos",
						forceFit:true,
						selType: 'cellmodel',
						plugins: [
							Ext.create('Ext.grid.plugin.CellEditing', {
								clicksToEdit: 2,
								listeners: {
									edit: function (editor, e) {
										Ext.getCmp("saveButton1").enable();
									}
								}
							})],
							listeners: {		
						        'edit': function (editor, e, opts) {
						        	if (e.field == 'softwareUpdate' && e.value == true){
						        		console.log("Pronto para mudar valores");
						        		console.log(e.record.data.softwareVersion);
						        		e.record.set('softwareVersion', '');
						        		e.record.set('updateDate', '');
						        	}
						        }
						    },
							dockedItems: [{
								xtype: 'toolbar',
								items: [{
									id: "saveButton1",
									text: 'Guardar',
									tooltip: 'Guardar as alteracoes selecionadas',
									disabled: true,
									iconCls: 'save',
									handler: function (btn) {
										var wantToSave = confirm("Tem a certeza? Ira guardar os equipamentos e SLAs selecionados");
										if (wantToSave) {
											equipmentStore.sync({
												success: function (batch, options) {
													alert("Registo(s) guardados na BD");
												},
												failure: function (batch, options) {
													alert("Erro a tentar guardar registo(s)");
												}
											});
										}
									}
								}]					
							}],	
						columns:[
							{
								text:"ID Equipment",
								dataIndex:"idEquipment",
								hidden: true		
							},
							{
								text:"Fabricante",
								dataIndex:"vendor",
								filter: {
									type: 'string'
								},
								editor: {
									xtype: 'textfield',
									allowBlank:false
								}				
							},
							{
								text:"Modelo",
								dataIndex:"model",
								filter: {
									type: 'string'
								},
								editor: {
									xtype: 'textfield'
								}				
							},
							{
								text:"Numero serie",
								dataIndex:"serialNumber",
								filter: {
									type: 'string'
								},
								editor: {
									xtype: 'textfield'
								}				
							},
							{
								text:"Inicio de contrato",
								xtype : 'datecolumn',
								format: 'd/m/Y',
								dataIndex:"contractStart",
								editor : {
						            xtype : 'datefield',
						            format: 'd/m/Y',
						            submitFormat: 'Y-m-d',
						            allowBlank:false
						        }
							},
							{
								text:"Fim de contrato",
								xtype : 'datecolumn',
								format: 'd/m/Y',
								dataIndex:"contractEnd",
								editor : {
						            xtype : 'datefield',
						            format: 'd/m/Y',
						            submitFormat: 'Y-m-d'
						        }
							},						
							{
								text:"Morada de instalacao",
								dataIndex:"installationAddress",
								width: 450,
								filter: {
									type: 'string'
								},
								editor: {
									xtype: 'textfield'
								}	
							},
							{
								text:"Data de instalacao",
								xtype : 'datecolumn',
								format: 'd/m/Y',
								dataIndex:"installationDate",
								editor : {
						            xtype : 'datefield',
						            format: 'd/m/Y',
						            submitFormat: 'Y-m-d'
						        }
							},
							{
								text:"idSla",
								dataIndex:"idSla",
								hidden: true					
							},
							{
								text:"SLA",
								dataIndex:"sla",
								filter: {
									type: 'string'
								},
								editor: {
									xtype: 'textfield',
									allowBlank:false
								}				
							},
							{
								text:"Ultima versao",
								dataIndex:"softwareLastVersion",
								filter: {
									type: 'string'
								},
								editor: {
									xtype: 'textfield'
								}				
							},
							{
								text:"Software Update",
								dataIndex:"softwareUpdate",
								renderer: function (value, metaData, record, rowIndex, colIndex, store, view){
									if (value == true){
										return "Sim";
									}else{
										return "Nao";
									}
								},
								editor: new Ext.form.ComboBox({
					                displayField: 'label',
					                editable: false,
					                forceSelection: true,
					                mode: 'local',
					                store: boolStoreOpts,
					                triggerAction: 'all',
					                valueField: 'opt'
					            }),
							},
							
							{
								text:"Versao software",
								dataIndex:"softwareVersion",
								filter: {
									type: 'string'
								},
								editor: {
									xtype: 'textfield'
								},
								
							},
							{
								text:"Data Update",
								xtype : 'datecolumn',
								format: 'd/m/Y',
								dataIndex:"updateDate",
								editor : {
						            xtype : 'datefield',
						            format: 'd/m/Y',
						            submitFormat: 'Y-m-d'
						        }
							},
							{
								text:"Id Customer",
								dataIndex:"idCustomer",
								hidden: true		
							},
							{
								text:"Opcoes",
								xtype: "actioncolumn",
								items:[
									{
										tooltip:"Eliminar",
										iconCls:"icon-delete",
										handler: function(grid,rowNum,colNum) {
											var wantToDelete = confirm("Tem a certeza? Ira eliminar apenas o equipamento e SLA");
											if (wantToDelete){
												equipmentStore.removeAt(rowNum);
												equipmentStore.sync({
													success: function (batch, options) {
														alert("Registo(s) eliminado da BD");
													},
													failure: function (batch, options) {
														alert("Erro a tentar eliminar registo(s)");
													}
												});
											}										
										}											
									}
								]
							}
						]
				    }]
//				    renderTo: Ext.getBody()
				});
			});