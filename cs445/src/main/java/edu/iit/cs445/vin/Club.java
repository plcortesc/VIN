package edu.iit.cs445.vin;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;

import edu.iit.cs445.Management.AddAdmin;
import edu.iit.cs445.Management.AddSubscriber;
import edu.iit.cs445.Management.DeliverTo;
import edu.iit.cs445.Management.ModifySubscriber;
import edu.iit.cs445.Management.MonthlySelectionType;
import edu.iit.cs445.Management.ShipmentStatus;
import edu.iit.cs445.Management.WineRating;
import edu.iit.cs445.Response.AddSubsResponse;
import edu.iit.cs445.Response.AdminDetailResponse;
import edu.iit.cs445.Response.AdminResponse;
import edu.iit.cs445.Response.GetAdminResponse;
import edu.iit.cs445.Response.GetReceiptsResponse;
import edu.iit.cs445.Response.GetShipmentsResponse;
import edu.iit.cs445.Response.GetWineNotesResponse;
import edu.iit.cs445.Response.GetWinesResponse;
import edu.iit.cs445.Response.ModifySubsResponse;
import edu.iit.cs445.Response.MonthlyResponse;
import edu.iit.cs445.Response.MonthlySelectionResponse;
import edu.iit.cs445.Response.MstDetailResponse;
import edu.iit.cs445.Response.NoteIDResponse;
import edu.iit.cs445.Response.NoteResponse;
import edu.iit.cs445.Response.NoteResponseSearch;
import edu.iit.cs445.Response.PostMsResponse;
import edu.iit.cs445.Response.PostNoteResponse;
import edu.iit.cs445.Response.ReceiptDetailResponse;
import edu.iit.cs445.Response.ReceiptResponse;
import edu.iit.cs445.Response.RevenueResponse;
import edu.iit.cs445.Response.SearchResponse;
import edu.iit.cs445.Response.ShipNotesResponse;
import edu.iit.cs445.Response.ShipmentDetailResponse;
import edu.iit.cs445.Response.ShipmentResponse;
import edu.iit.cs445.Response.SubscriberResponse;
import edu.iit.cs445.Response.ToDeliverResponse;
import edu.iit.cs445.Response.WineDetailResponse;
import edu.iit.cs445.Response.WineResponse;

public class Club implements java.io.Serializable {
	
	private static final long serialVersionUID = -4601574179158106799L;

	public Club() {}
	
	public AddSubsResponse addSubscriber(ArrayList<Subscriber> subs, String st, String c, String s, String z,
			String n, String e, String p, String tw, String fb){
		AddSubscriber sr = new AddSubscriber(st,c,s,z,n,e,p,tw,fb);
		AddSubsResponse ssd = sr.addAccount(subs,null);
		
		return ssd;
	}
	
	public ModifySubsResponse setSubscriber(int UID, String name, String email, String phone, String twitter, 
			String facebook,String street, String city, String state, String zip, ArrayList<Subscriber> subs){
		ModifySubsResponse msr = null;
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				String n=name==null ? subscriber.getName():name;
				String e=email==null ? subscriber.getEmail():email;
				String p=phone==null ? subscriber.getPhone():phone;
				String tw=twitter==null ? subscriber.getTwitter():twitter;
				String fb=facebook==null ? subscriber.getFacebook():facebook;
				String st=street==null ? subscriber.getAddress().getStreet():street;
				String c=city==null ? subscriber.getAddress().getCity():city;
				String s=state==null ? subscriber.getAddress().getState():state;
				String z=zip==null ? subscriber.getAddress().getZip():zip;
				
				MonthlySelectionType mst = subscriber.getPreference();
			    String date = subscriber.getDate();
			    ArrayList<Shipment> shipments = subscriber.getShipments();
				ArrayList<Wine> wines = subscriber.getWines();
								
				ModifySubscriber msub = new ModifySubscriber(st,c,s,z,n,e,p,tw,fb);
				msr = msub.modifyAccount(subs, UID,mst,date,shipments,wines);
				if(msr.getErrors().size()==0) subs.remove(subscriber);
				break;
			}
		}
		return msr;
	}
	
	public SubscriberResponse printSubscriber(int UID,ArrayList<Subscriber> subs){
		SubscriberResponse sr = null;
		for(Subscriber s: subs){
			if(s.getID()==UID){
				sr = new SubscriberResponse(s.getEmail(),s.getName(),s.getPhone(),s.getAddress(),s.getFacebook(),s.getTwitter());
			}
		}
		return sr;
	}
	
	public SearchResponse search(int UID, String kw,ArrayList<Subscriber> subs){
		SearchResponse sr = null;
		for(Subscriber s: subs){
			if(s.getID()==UID){
				sr = new SearchResponse(s.searchWine(kw),s.searchShipment(kw),s.searchNote(kw));
			}
		}
		return sr;
	}
	
	public GetShipmentsResponse printSubShipment(int UID,ArrayList<Subscriber> subs){
		ArrayList<ShipmentResponse> sr = new ArrayList<ShipmentResponse>();
		for(Subscriber s: subs){
			if(s.getID()==UID){
				if(s.getShipments()!=null){
					for(Shipment ship: s.getShipments()){
						sr.add(new ShipmentResponse(ship.getSID(),ship.getYm(),ship.getStatus()));
					}
				}
			}
		}
		return new GetShipmentsResponse(sr);
	}
	
	public ShipmentDetailResponse printSubShipSID(int UID, int SID,ArrayList<Subscriber> subs){
		ShipmentDetailResponse sdr = null;
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Shipment shipment: subscriber.getShipments()){
					if(shipment.getSID()==SID){
						String date = Integer.toString(shipment.getDate().get(Calendar.DAY_OF_MONTH))+
									  "/"+Integer.toString(shipment.getDate().get(Calendar.MONTH))+
									  "/"+Integer.toString(shipment.getDate().get(Calendar.YEAR));
						ArrayList<WineResponse> wrs = new ArrayList<WineResponse>();
						for(Wine w:shipment.getMsWines()){
							wrs.add(new WineResponse(w.getWID(),w.getLabelName()));
						}
						sdr = new ShipmentDetailResponse(shipment.getYm(),shipment.getStatus(), date, shipment.getMst(), wrs);
						
					}
				}
			}
		}return sdr;			
	}
	
	public ShipNotesResponse printShipNote(int UID, int SID,ArrayList<Subscriber> subs){
		ArrayList<NoteResponse> snr = new ArrayList<NoteResponse>();
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Shipment shipment: subscriber.getShipments()){
					if(shipment.getSID()==SID && shipment.getNotes()!=null){
						for(Note n: shipment.getNotes()){
							String date = Integer.toString(n.getDate().get(Calendar.DAY_OF_MONTH))+
									  "/"+Integer.toString(n.getDate().get(Calendar.MONTH))+
									  "/"+Integer.toString(n.getDate().get(Calendar.YEAR));
							snr.add(new NoteResponse(n.getNID(),date,n.getContent()));
						}
						
					}
				}
			}
		}return new ShipNotesResponse(snr);
	}
	
	public PostNoteResponse postShipmentNote(int UID, int SID, Note n,ArrayList<Subscriber> subs){
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Shipment shipment: subscriber.getShipments()){
					if(shipment.getSID()==SID){
						shipment.addNote(n);
					}
				}
			}
		}return new PostNoteResponse(n.getNID());
	}
	
	public NoteIDResponse printShipNoteNID(int UID, int SID, int NID,ArrayList<Subscriber> subs){
		NoteIDResponse nr = null;
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Shipment shipment: subscriber.getShipments()){
					if(shipment.getSID()==SID){
						for(Note note: shipment.getNotes()){
							if(note.getNID()==NID){
								String date = Integer.toString(note.getDate().get(Calendar.MONTH))+
										  "/"+Integer.toString(note.getDate().get(Calendar.DAY_OF_MONTH))+
										  "/"+Integer.toString(note.getDate().get(Calendar.YEAR));
								nr = new NoteIDResponse(date,note.getContent());
							}
						}
					}
				}
			}
		}return nr;
	}
	
	public void setShipNoteNID(int UID, int SID, int NID, Note n,ArrayList<Subscriber> subs){
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Shipment shipment: subscriber.getShipments()){
					if(shipment.getSID()==SID){
						shipment.updateNote(NID, n);
					}
				}
			}
		}
	}
	
	public void removeShipNoteNID(int UID, int SID, int NID,ArrayList<Subscriber> subs){
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Shipment shipment: subscriber.getShipments()){
					if(shipment.getSID()==SID){
						shipment.removeNote(NID);
					}
				}
			}
		}
	}
		
	public GetWinesResponse printWines(int UID,ArrayList<Subscriber> subs){
		ArrayList<WineResponse> wrs = new ArrayList<WineResponse>();
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Wine w: subscriber.getWines()){
					wrs.add(new WineResponse(w.getWID(),w.getLabelName()));
				}	
			}
		}return new GetWinesResponse(wrs);
	}
	
	public WineResponse printWinesWID(int UID, int WID,ArrayList<Subscriber> subs){
		WineResponse wr = null;
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Wine w: subscriber.getWines()){
					if(w.getWID()==WID){
						wr = new WineResponse(w.getWID(),w.getLabelName());
					}
				}				
			}
		}return wr;
	}
	
	public GetWineNotesResponse printWineNotes(int UID, int WID,ArrayList<Subscriber> subs){
		ArrayList<NoteResponseSearch> nrs = new ArrayList<NoteResponseSearch>();
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Wine w: subscriber.getWines()){
					if(w.getWID()==WID && w.getNotes()!=null){
						for(Note n: w.getNotes()){
							nrs.add(new NoteResponseSearch(n.getNID(),n.getContent()));
						}
					}
				}
			}
		}return new GetWineNotesResponse(nrs);
	}
	
	public PostNoteResponse postWinesNote(int UID, int WID, Note n,ArrayList<Subscriber> subs){
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Wine w: subscriber.getWines()){
					if(w.getWID()==WID){
						w.addNote(n);
					}
				}
			}
		}return new PostNoteResponse(n.getNID());
	}
	
	public NoteResponseSearch printWineNoteNID(int UID, int WID, int NID,ArrayList<Subscriber> subs){
		NoteResponseSearch nrs = null;
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Wine w: subscriber.getWines()){
					if(w.getWID()==WID){
						for(Note note: w.getNotes()){
							if(note.getNID()==NID){
								nrs = new NoteResponseSearch(note.getNID(),note.getContent());
							}
						}
					}
				}
			}
		}return nrs;
	}
	
	public void setWineNoteNID(int UID, int WID, int NID, Note n,ArrayList<Subscriber> subs){
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Wine w: subscriber.getWines()){
					if(w.getWID()==WID){
						w.updateNote(NID, n);
					}
				}
			}
		}
	}
	
	public void removeWineNoteNID(int UID, int WID, int NID,ArrayList<Subscriber> subs){
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Wine w: subscriber.getWines()){
					if(w.getWID()==WID){
						w.removeNote(NID);
					}
				}
			}
		}
	}
	
	public WineRating printWineRating(int UID, int WID,ArrayList<Subscriber> subs){
		WineRating wr = null;
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Wine w: subscriber.getWines()){
					if(w.getWID()==WID){
						wr = new WineRating(w.getRating());
					}
				}
			}
		}return wr;
	}
	
	public void postWineRating(int UID, int WID, int r,ArrayList<Subscriber> subs){
		for(Subscriber subscriber: subs){
			if(subscriber.getID()==UID){
				for(Wine w: subscriber.getWines()){
					if(w.getWID()==WID){
						System.out.println("Hellodolly");
						w.addRating(r);;
					}
				}
			}
		}
	}
	
	public PostNoteResponse addAdmin(String name,ArrayList<Admin> admin){
		AddAdmin aa = new AddAdmin(name);
		PostNoteResponse pnr = aa.addAccount(admin);
		return pnr;
	}
	
	public GetAdminResponse printAdmin(ArrayList<Admin> admin){
		ArrayList<AdminResponse> admins = new ArrayList<AdminResponse>();
		for(Admin a: admin){
			admins.add(new AdminResponse(a.getAID(),a.getName(),a.getDate()));
		}return new GetAdminResponse(admins);
	}
	
	public void setAdmin(String name, int AID,ArrayList<Admin> admin){
		for(Admin a: admin){
			if(a.getAID()==AID){
				a.setName(name);;
			}
		}
	}
	
	public AdminDetailResponse printAdminAID(int AID,ArrayList<Admin> admin){
		AdminDetailResponse adr = null;
		for(int i=0;i<admin.size();i++){
			if(admin.get(i).getAID()==AID){
				int created_by;
				if(i>0){created_by = admin.get(i-1).getAID();}
				else{ created_by = admin.get(i).getAID();}
				adr = new AdminDetailResponse(admin.get(i).getAID(), admin.get(i).getName(), admin.get(0).getDate(), created_by);
			}
		}return adr;	
	}
	
	public RevenueResponse adminRevenue(ArrayList<Subscriber> subs){
		int units_delivered=0;
		int wine_revenue=0;
		int delivery_revenue=0;
		
		for(Subscriber s: subs){
			for(Shipment ship: s.getShipments()){
				if(ship.getStatus()==ShipmentStatus.DELIVERED){
					units_delivered += ship.getSize() * 6;
					wine_revenue += ship.getSize()*ship.getMsDeliveryfee();
					delivery_revenue += ship.getDeliveryfee();
				}
			}

		}
		return new RevenueResponse(units_delivered, wine_revenue, delivery_revenue);
	}
		
	public PostMsResponse postMs(String mst, String ym, ArrayList<Wine> wines,ArrayList<MonthlySelection> monthlySelection,
			ArrayList<Admin> admin, ArrayList<Subscriber> subs) {
		int MID = admin.get(0).createMs(mst, ym, wines,monthlySelection, subs);
		PostMsResponse pmr = new PostMsResponse();
		pmr.setMID(MID);
		return pmr;
	}
	
	public MonthlySelectionResponse printMs(ArrayList<MonthlySelection> monthlySelection){
		ArrayList<MonthlyResponse> mr = new ArrayList<MonthlyResponse>();
		for(MonthlySelection ms: monthlySelection){
			mr.add(new MonthlyResponse(ms.getID(),ms.getYm(),ms.getMst()));
		}
		return new MonthlySelectionResponse(mr);
	}

	public MstDetailResponse printMsMID(int MID,ArrayList<MonthlySelection> monthlySelection){
		MstDetailResponse mdr = null;
		ArrayList<WineResponse> wr = new ArrayList<WineResponse>();
		for(MonthlySelection ms: monthlySelection){
			if(ms.getID()==MID){
				String date = ms.getDate();
				for(Wine w: ms.getMs()){
					wr.add(new WineResponse(w.getWID(),w.getLabelName()));
				}
				mdr = new MstDetailResponse(MID,ms.getMst(),ms.getYm(),date,ms.getAID(),wr);
			}
		}return mdr;
	}
		
	public ToDeliverResponse shipmentsToDeliver(ArrayList<Subscriber> subs){
		YearMonth ym = YearMonth.now();
		ArrayList<DeliverTo> dt = new ArrayList<DeliverTo>();
		ArrayList<Subscriber> subsToDeliver = new ArrayList<Subscriber>();
		for(Subscriber s:subs){
			for(Shipment ship: s.getShipments()){
				if(ship.getStatus()==ShipmentStatus.PENDING &&(ship.getYm().equals(Integer.toString(ym.getYear())+"-"+Integer.toString(ym.getMonthValue())))){
					subsToDeliver.add(s);
					dt.add(new DeliverTo(s.getName(),s.getPhone(),s.getEmail(),s.getAddress(),s.getPreference()));
				}
			}
		}
		return new ToDeliverResponse(dt);
	}
	
	public PostNoteResponse postReceipt(int SID, String nameReceiver, int age, ArrayList<Receipt> receipts,ArrayList<Subscriber> subs){
		Receipt r = null;
		for(Subscriber s: subs){
			for(Shipment ship: s.getShipments()){
				if(ship.getStatus()==ShipmentStatus.PENDING && ship.getSID()==SID){
					ship.setStatus(ShipmentStatus.DELIVERED);
					if(age>21){
						r = new Receipt(s,nameReceiver);
						receipts.add(r);
					}
				}
			}
		}return new PostNoteResponse(r.getID());
	}
	
	public GetReceiptsResponse printReceipts(ArrayList<Receipt> receipts){
		ArrayList<ReceiptResponse> rr = new ArrayList<ReceiptResponse>();
		for(Receipt r: receipts){
			String date = Integer.toString(r.getDate().get(Calendar.DAY_OF_MONTH))+"-"
					 +Integer.toString(r.getDate().get(Calendar.MONTH))+"-"+Integer.toString(r.getDate().get(Calendar.YEAR));
			rr.add(new ReceiptResponse(r.getID(),date, r.getSID(),r.getName()));
		}return new GetReceiptsResponse(rr);
	}
	
	public ReceiptDetailResponse printReceiptRID(int RID, ArrayList<Receipt> receipts){
		ReceiptDetailResponse rdr = null;
		for(Receipt r: receipts){
			if(r.getID()==RID){
				String time = Integer.toString(r.getDate().get(Calendar.HOUR))+":"
						 +Integer.toString(r.getDate().get(Calendar.MINUTE));
				String date = Integer.toString(r.getDate().get(Calendar.DAY_OF_MONTH))+"-"
						 +Integer.toString(r.getDate().get(Calendar.MONTH))+"-"+Integer.toString(r.getDate().get(Calendar.YEAR));
				rdr = new ReceiptDetailResponse(r.getID(),time, date, r.getSID(),r.getName(),r.getReceiver());
			}
		}return rdr;
	}
	
	public WineDetailResponse printAllWinesWID(int WID, ArrayList<Subscriber> subs){
		for(Subscriber subscriber: subs){
			for(Wine w: subscriber.getWines()){
				if(w.getWID()==WID){
					return new WineDetailResponse(WID, w.getVariety(),w.getType(), w.getLabelName(),w.getGrape(),
							w.getRegion(), w.getCountry(),w.getMaker(),w.getYear(),w.getNumberOfRatings(), w.getRating());
				}
			}
		}return null;
	}
}
