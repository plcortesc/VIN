package edu.iit.cs445.vin;

import java.io.*;
import java.util.ArrayList;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.DeserializationConfig;

public class Main {
	
	public static void main(String[] args) throws ParseException, JsonGenerationException, JsonMappingException, IOException {	
		Club c = new Club();
		ClubData data = new ClubData();
		data.setClubName("VIN");
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
	    mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {
			data = mapper.readValue(new FileInputStream("pablo-cortes.ser"),ClubData.class);
        } catch (Exception e) {
            System.out.println("Creating new file");
            data.admin = new ArrayList<Admin>();
            data.monthlySelection = new ArrayList<MonthlySelection>();
            data.receipts = new ArrayList<Receipt>();
            data.subs = new ArrayList<Subscriber>();
        }

		// create Options object
		Options options = new Options();
		// add t option
		options.addOption("subscriber", false, "commands for subscriber");
		options.addOption("shipments", false, "commands for shipments");
		options.addOption("notes", false, "commands for notes");
		options.addOption("wines", false, "commands for wine");
		options.addOption("admin", false, "commands for admin");
		options.addOption("partner", false, "commands for partner");

		options.addOption("add", false, "add some stuff");
		options.addOption("modify", false, "modify some stuff");
		options.addOption("view", false, "view some stuff");
		options.addOption("load", false, "load some file");
		options.addOption("search", false, "search some coincidence");
		options.addOption("delete", false, "delete some stuff");
		options.addOption("rank", false, "display wine ranking");
		options.addOption("revenue", false, "display revenue");
		options.addOption("add_monthly_selection", false, "add a monthly selection");
		options.addOption("view_monthly_selection", false, "display monthly selection");
		options.addOption("subscriber_list", false, "display subscribers to deliver");
		options.addOption("add_receipt", false, "add receipt");
		options.addOption("view_receipt", false, "display receipts");
		
		options.addOption("n", true, "name");
		options.addOption("e", true, "email");
		options.addOption("a", true, "street");
		options.addOption("c", true, "city");
		options.addOption("s", true, "state");
		options.addOption("z", true, "zip");
		options.addOption("h", true, "phone");
		options.addOption("f", true, "facebook/file");
		options.addOption("t", true, "twitter");
		options.addOption("k", true, "keyword");
		options.addOption("r", true, "rating");
		options.addOption("m", true, "message content");
		options.addOption("age", true, "receiver's age");
		options.addOption("nr", true, "receiver's name");
		options.addOption("uid", true, "subscriber id");
		options.addOption("aid", true, "admin id");
		options.addOption("sid", true, "shipment id");
		options.addOption("rid", true, "receipt id");
		options.addOption("nid", true, "note id");
		options.addOption("wid", true, "wine id");
		options.addOption("mid", true, "monthly selection id");
		options.addOption("mst", true, "monthly selection type string");
		options.addOption("ym", true, "ms date string, yyyy-mm format");


		CommandLine line = new BasicParser().parse(options, args);
		
		if(line.hasOption("subscriber")){
			if(line.hasOption("add")){
				AddSubsResponse asr = c.addSubscriber(data.subs,line.getOptionValue("a"), line.getOptionValue("c"), line.getOptionValue("s"), 
									  line.getOptionValue("z"), line.getOptionValue("n"), line.getOptionValue("e"), 
									  line.getOptionValue("h"), line.getOptionValue("t"), line.getOptionValue("f"));
                System.out.println(mapper.writeValueAsString(asr));

			}else if(line.hasOption("modify")){
				if(line.hasOption("uid")){
					AddSubsResponse asr = c.setSubscriber(Integer.parseInt(line.getOptionValue("uid")),  
											line.getOptionValue("n"), line.getOptionValue("e"),
											line.getOptionValue("h"), line.getOptionValue("tw"), line.getOptionValue("fb"),data.subs);
					System.out.println(mapper.writeValueAsString(asr));
				}
			}else if(line.hasOption("view")){
				if(line.hasOption("uid")){
					SubscriberResponse sr = c.printSubscriber(Integer.parseInt(line.getOptionValue("uid")),data.subs);
					System.out.println(mapper.writeValueAsString(sr));
				}
			}else if(line.hasOption("load")){
				if(line.hasOption("f")){
					Subscriber sub= mapper.readValue(new FileInputStream(line.getOptionValue("f")),Subscriber.class);
			       
					AddSubsResponse asr = c.addSubscriber(data.subs,sub.getAddress().getStreet(), sub.getAddress().getCity(), 
										  sub.getAddress().getState(),sub.getAddress().getZip(), sub.getName(), sub.getEmail(), 
										  sub.getPhone(), sub.getTwitter(), sub.getFacebook());
					System.out.println(mapper.writeValueAsString(asr));
				}
				
			}else if(line.hasOption("search")){
				if(line.hasOption("k")){
					SearchResponse sr = c.search(Integer.parseInt(line.getOptionValue("uid")),line.getOptionValue("k"),data.subs);
					System.out.println(mapper.writeValueAsString(sr));
				}
			}
			
		}else if(line.hasOption("shipments")){
			if(line.hasOption("view")){
				if(line.hasOption("uid")&&line.hasOption("sid")){
					ShipmentDetailResponse sdr = c.printSubShipSID(Integer.parseInt(line.getOptionValue("uid")), Integer.parseInt(line.getOptionValue("sid")),data.subs);
					System.out.println(mapper.writeValueAsString(sdr));
				}else if(line.hasOption("uid")){
					GetShipmentsResponse gsr = c.printSubShipment(Integer.parseInt(line.getOptionValue("uid")),data.subs);
					System.out.println(mapper.writeValueAsString(gsr));
				}		
			}else if(line.hasOption("modify")){}
			
		}else if(line.hasOption("notes")){
			if(line.hasOption("view")){
				if(line.hasOption("uid")&&line.hasOption("sid")&&line.hasOption("nid")){
					NoteIDResponse nir = c.printShipNoteNID(Integer.parseInt(line.getOptionValue("uid")), Integer.parseInt(line.getOptionValue("sid")),
							Integer.parseInt(line.getOptionValue("nid")),data.subs);
					System.out.println(mapper.writeValueAsString(nir));
				}else if(line.hasOption("uid")&&line.hasOption("sid")){
					ShipNotesResponse snr = c.printShipNote(Integer.parseInt(line.getOptionValue("uid")), Integer.parseInt(line.getOptionValue("sid")),data.subs);
					System.out.println(mapper.writeValueAsString(snr));
				}else if(line.hasOption("uid")&&line.hasOption("wid")&&line.hasOption("nid")){
					NoteResponseSearch nrs = c.printWineNoteNID(Integer.parseInt(line.getOptionValue("uid")), Integer.parseInt(line.getOptionValue("wid")), 
											 Integer.parseInt(line.getOptionValue("nid")),data.subs);
					System.out.println(mapper.writeValueAsString(nrs));
				}else if(line.hasOption("uid")&&line.hasOption("wid")){
					GetWineNotesResponse grn = c.printWineNotes(Integer.parseInt(line.getOptionValue("uid")), Integer.parseInt(line.getOptionValue("wid")),data.subs);
					System.out.println(mapper.writeValueAsString(grn));
				}
			}else if(line.hasOption("add")){
				if(line.hasOption("uid")&&line.hasOption("sid")&&line.hasOption("m")){
					Note n = new Note(line.getOptionValue("m"));
					PostNoteResponse pnr = c.postShipmentNote(Integer.parseInt(line.getOptionValue("uid")), Integer.parseInt(line.getOptionValue("sid")),
										   n,data.subs);
					System.out.println(mapper.writeValueAsString(pnr));
				}else if(line.hasOption("uid")&&line.hasOption("wid")&&line.hasOption("m")){
					Note n = new Note(line.getOptionValue("m"));
					PostNoteResponse pnr = c.postWinesNote(Integer.parseInt(line.getOptionValue("uid")), Integer.parseInt(line.getOptionValue("sid")), 
							 			   n,data.subs);
					System.out.println(mapper.writeValueAsString(pnr));
				}
			}else if(line.hasOption("modify")){
				if(line.hasOption("uid")&&line.hasOption("sid")&&line.hasOption("nid")&&line.hasOption("m")){
					Note n = new Note(line.getOptionValue("m"));
					c.setShipNoteNID(Integer.parseInt(line.getOptionValue("uid")), Integer.parseInt(line.getOptionValue("sid")),
							Integer.parseInt(line.getOptionValue("nid")),n,data.subs);
				}else if(line.hasOption("uid")&&line.hasOption("wid")&&line.hasOption("nid")&&line.hasOption("m")){
					Note n = new Note(line.getOptionValue("m"));
					c.setWineNoteNID(Integer.parseInt(line.getOptionValue("uid")), Integer.parseInt(line.getOptionValue("wid")),
							Integer.parseInt(line.getOptionValue("nid")),n,data.subs);
				}
			}else if(line.hasOption("delete")){
				if(line.hasOption("uid")&&line.hasOption("sid")&&line.hasOption("nid")){
					c.removeShipNoteNID(Integer.parseInt(line.getOptionValue("uid")), Integer.parseInt(line.getOptionValue("sid")),
							Integer.parseInt(line.getOptionValue("nid")),data.subs);
				}else if(line.hasOption("uid")&&line.hasOption("wid")&&line.hasOption("nid")){
					c.removeWineNoteNID(Integer.parseInt(line.getOptionValue("uid")), Integer.parseInt(line.getOptionValue("wid")),
							Integer.parseInt(line.getOptionValue("nid")),data.subs);
				}
			}
			
		}else if(line.hasOption("wines")){
			if(line.hasOption("view")){
				if(line.hasOption("uid")&&line.hasOption("wid")){
					WineResponse wr = c.printWinesWID(Integer.parseInt(line.getOptionValue("uid")), Integer.parseInt(line.getOptionValue("wid")),data.subs);
					System.out.println(mapper.writeValueAsString(wr));
				}else if(line.hasOption("uid")){
					GetWinesResponse gwr = c.printWines(Integer.parseInt(line.getOptionValue("uid")),data.subs);
					System.out.println(mapper.writeValueAsString(gwr));
				}else if(line.hasOption("wid")){
					WineDetailResponse wdr = c.printAllWinesWID(Integer.parseInt(line.getOptionValue("wid")), data.subs);
					System.out.println(mapper.writeValueAsString(wdr));
				}
			}else if(line.hasOption("rank")){
				if(line.hasOption("view")&&line.hasOption("uid")&&line.hasOption("wid")){
					WineRating wr = c.printWineRating(Integer.parseInt(line.getOptionValue("uid")), Integer.parseInt(line.getOptionValue("wid")),data.subs);
					System.out.println(mapper.writeValueAsString(wr));
				}else if(line.hasOption("add")&&line.hasOption("uid")&&line.hasOption("wid")){
					c.postWineRating(Integer.parseInt(line.getOptionValue("uid")), Integer.parseInt(line.getOptionValue("wid")), Integer.parseInt(line.getOptionValue("rat")),data.subs);
				}
				
			}
		}else if(line.hasOption("admin")){
			if(line.hasOption("add")&&line.hasOption("n")){
				PostNoteResponse pnr = c.addAdmin(line.getOptionValue("n"),data.admin);
				System.out.println(mapper.writeValueAsString(pnr));
			}else if(line.hasOption("view")){
				if(line.hasOption("aid")){
					AdminDetailResponse adr = c.printAdminAID(Integer.parseInt(line.getOptionValue("aid")),data.admin);
					System.out.println(mapper.writeValueAsString(adr));
				}else{
					GetAdminResponse gar = c.printAdmin(data.admin);
					System.out.println(mapper.writeValueAsString(gar));
				}
			}else if(line.hasOption("modify")){
				if(line.hasOption("n")&&(line.hasOption("aid"))){
					c.setAdmin(line.getOptionValue("n"), Integer.parseInt(line.getOptionValue("aid")),data.admin);
				}
			}else if(line.hasOption("revenue")){
				RevenueResponse rr = c.adminRevenue(data.subs);
				System.out.println(mapper.writeValueAsString(rr));
			}else if(line.hasOption("add_monthly_selection")&&line.hasOption("mst")&&line.hasOption("ym")&&line.hasOption("f")){
				MsWinePost wp = mapper.readValue(new FileInputStream(line.getOptionValue("f")),MsWinePost.class);
				c.postMs(line.getOptionValue("mst"), line.getOptionValue("ym"), wp.wines, data.monthlySelection, data.admin);
			}else if(line.hasOption("view_monthly_selection")&&line.hasOption("mid")){
				MstDetailResponse mdr = c.printMsMID(Integer.parseInt(line.getOptionValue("mid")),data.monthlySelection);
				System.out.println(mapper.writeValueAsString(mdr));
			}else if(line.hasOption("view_monthly_selection")){
				MonthlySelectionResponse msr = c.printMs(data.monthlySelection);
				System.out.println(mapper.writeValueAsString(msr));
			}
			
		}else if(line.hasOption("partner")){
			if(line.hasOption("subscriber_list")){
				ToDeliverResponse tdr = c.shipmentsToDeliver(data.subs);
				System.out.println(mapper.writeValueAsString(tdr));
			}else if(line.hasOption("add_receipt")){
				if(line.hasOption("sid")&&line.hasOption("nr")&&line.hasOption("age")){
					PostNoteResponse pnr = c.postReceipt(Integer.parseInt(line.getOptionValue("sid")), line.getOptionValue("nr"), 
										   Integer.parseInt(line.getOptionValue("age")), data.receipts,data.subs);
					System.out.println(mapper.writeValueAsString(pnr));
				}
			}else if(line.hasOption("view_receipt")){
				if(line.hasOption("rid")){
					ReceiptDetailResponse rdr = c.printReceiptRID(Integer.parseInt(line.getOptionValue("rid")), data.receipts);
					System.out.println(mapper.writeValueAsString(rdr));
				}else{
					GetReceiptsResponse grr = c.printReceipts(data.receipts);
					System.out.println(mapper.writeValueAsString(grr));
				}
			}		
		}

		try {
            mapper.writeValue(new FileOutputStream("pablo-cortes.ser"), data);
        } catch (IOException e) {
        	System.out.println("IOException caught, error saving state");
        }	
		
	}
}
