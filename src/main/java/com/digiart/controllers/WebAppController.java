package com.digiart.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.digiart.domain.Avatar;
import com.digiart.domain.Categorie;
import com.digiart.domain.Commentaire;
import com.digiart.domain.Offre;
import com.digiart.domain.Publication;
import com.digiart.domain.Request;
import com.digiart.domain.Role;
import com.digiart.domain.User;
import com.digiart.repositories.AvatarRepository;
import com.digiart.repositories.CategorieRepository;
import com.digiart.repositories.CommentaireRepository;
import com.digiart.repositories.OffreRepository;
import com.digiart.repositories.PublicationRepository;
import com.digiart.repositories.RequestRepository;
import com.digiart.repositories.RoleRepository;
import com.digiart.repositories.UserRepository;
import com.digiart.service.FileUploadUtil;

@Controller
public class WebAppController {
	
	 @Autowired
	 private UserRepository userRepo;
	 @Autowired
	 private AvatarRepository avatarRepo;
	 @Autowired
	 private PublicationRepository pubRepo;
	 @Autowired
	 private CategorieRepository categorieRepo;
	 @Autowired
	 private OffreRepository OffreRepo;
	 @Autowired
	 private RequestRepository RequestRepo;
	 @Autowired
	 private RoleRepository RoleRepo;
	 @Autowired
	 private CommentaireRepository commRepo;
	     
	     
	 @GetMapping("")
	 public String viewHomePage() {
		 return "index";
	 }
	 @GetMapping("/login")
	 public String viewLoginPage() {
		 return "login";
	 }
	 @GetMapping("/")
	 public String viewHome(Model model) {

		 List<User> listUsersEnabled = userRepo.findAllEnabled();
		 model.addAttribute("listUsersEnabled", listUsersEnabled);
		 List<User>  listUsers = userRepo.findAll();
		 model.addAttribute("listUsers", listUsers); 
		 List<User> listSortedUsers = userRepo.findAndSortByFort();
		 model.addAttribute("sortedUsers", listSortedUsers);
		 List<Publication> listPubs = pubRepo.findAllpubs();
		 model.addAttribute("listPubs", listPubs);  
		 List<Avatar> listAvatar = avatarRepo.findAll();
		 model.addAttribute("listAvatars", listAvatar);
		 
		 return "index";
	 }
	 @GetMapping("/explore")
	 public String viewExplore(Model model) {

		 List<User> listUsers = userRepo.findAll();
		 model.addAttribute("listUsers", listUsers); 
		 List<Publication> listPubs = pubRepo.findAll();
		 model.addAttribute("listPubs", listPubs);  
		 List<Avatar> listAvatar = avatarRepo.findAll();
		 model.addAttribute("listAvatars", listAvatar);
		 
		 return "explore";
	 }
		
	 @GetMapping("/admin/users")
	 public String viewAdminUsers(Model model) {

		 List<User> listUsers = userRepo.findAll();
		 model.addAttribute("listUsers", listUsers); 
		 List<Avatar> listAvatar = avatarRepo.findAll();
		 model.addAttribute("listAvatars", listAvatar);
		
		 
		 return "users-admin";
	 }
	 @GetMapping("/admin/requests")
	 public String viewAdminRequests(Model model) {

		 	List<Request> Listreq = RequestRepo.findAll();
			model.addAttribute("listReq", Listreq);
			 List<User> listUsers = userRepo.findAll();
			 model.addAttribute("listUsers", listUsers);  
			 List<Avatar> listAvatar = avatarRepo.findAll();
			 model.addAttribute("listAvatars", listAvatar);
			
			

		
		 
		 return "requests-admin";
	 }
	 @PostMapping("/remove_request")
	 public String removerequest(@RequestParam("request") Integer id) {

		 Request req = RequestRepo.findByRequestID(id);
		 RequestRepo.delete(req);	 
		 return "requests-admin";
	 }
	 
	 @PostMapping("/accept_request")
	 public RedirectView Acceptrequest(@RequestParam("request") Integer id, @RequestParam("participant") String username) {

		 Request req = RequestRepo.findByRequestID(id);
		 User user = userRepo.findByUsername(username);
		 Set<Role> s = user.getRoles();
		 
		 Role R = RoleRepo.findByRoleId(2);
		 Role P = RoleRepo.findByRoleId(3);

		 s.add(R);
		 s.remove(P);
		 user.setRoles(s);
		 RequestRepo.delete(req);	 
		 return new RedirectView("/admin/requests");
	 }
		
		
		  @GetMapping("/add")
		  public String viewAddPage(Model model) { 

				 List<User> listUsers = userRepo.findAll();
				 model.addAttribute("listUsers", listUsers);
				 List<Avatar> listAvatar = avatarRepo.findAll();
				 model.addAttribute("listAvatars", listAvatar);
				 
			  List<Categorie> listCategorie = categorieRepo.findAll(); 
			  model.addAttribute("listCategorie", listCategorie);
			 
			  model.addAttribute("listUsers", listUsers); model.addAttribute("pub", new Publication());
			  return "create"; 
		  }
		 
	
	 
	 @GetMapping("/register")
	 public String showRegistrationForm(Model model) {
		
			
		 List<Avatar> listAvatar = avatarRepo.findAll();
		 model.addAttribute("listAvatars", listAvatar);
			 
	     model.addAttribute("user", new User());
	      
	     return "signup";
	 }
	 @GetMapping("/register1")
	 public String showRegisterForm(Model model) {
		
			
			  List<Avatar> listAvatar = avatarRepo.findAll();
			  model.addAttribute("listAvatars", listAvatar);
			 
	     model.addAttribute("user", new User());
	      
	     return "signup0";
	 }
	 @GetMapping("/list_users")
	 public String ViewUsersList(Model model) {
		 List<User> listUsers = userRepo.findAll();
		    model.addAttribute("listUsers", listUsers);
		     
		    return "users";
	      
	 }
	 @GetMapping("/list_pubs")
	 public String ViewPubsList(Model model) {
		 List<Publication> listPubs = pubRepo.findAll();
		    model.addAttribute("listPubs", listPubs);    
		    return "pub0";
	      
	 }
	 
	 @PostMapping("/process_register")
	 public String processRegister(User user) {
			
		 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		 String encodedPassword = passwordEncoder.encode(user.getMot_de_passe());
		 user.setMot_de_passe(encodedPassword);
		 
		 Set<Role> s = new HashSet<>();
		 
		 Role R = RoleRepo.findByRoleId(3);
		 s.add(R);
		 user.setRoles(s);
		
	     userRepo.save(user);
	      
	     return "login";
	 }
	 
	 @PostMapping("/remove_user")
	 public String processRemoveUser( @RequestParam("participant") String username) {
		 User user = userRepo.findByUsername(username);
		 
			 
	      
	     userRepo.delete(user);
	      
	     return "redirect:admin/users" ;
	 }
	 
	 @PostMapping("/enable_user")
	 public String EnableUser( @RequestParam("userforenabling") String username) {
		 User user = userRepo.findByUsername(username);
		 
		 if(user.isActive()) {
			 user.setActive(false);
			 
		 }else{
			 user.setActive(true);
			
		 }
	     userRepo.save(user);
	      
	     return "redirect:admin/users" ;
	 }
		
		
	@PostMapping("/creation_process") 
	public String processAddItem(@ModelAttribute Publication pub, @RequestParam("photo") MultipartFile multipartFile, @RequestParam("creator") String creator ) { 
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		System.out.println(fileName);
		pub.setImage(fileName);
		pub.setUsername(creator);
		
		Publication savedPub = pubRepo.save(pub);
		//pubRepo.save(pub); 
		String uploadDir = "src/main/resources/static/images/";
		try {
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/" ; }
	
	@RequestMapping(value = "/postdedtail/{id}", method = RequestMethod.GET)
	public String productDdetail(@PathVariable("id") Integer Id_publication, Model model) {

		Publication pub = pubRepo.getById(Id_publication);
		List<Offre> listOffre = OffreRepo.findAllByPublicationid(Id_publication, Sort.by("prix").descending());
		model.addAttribute("listOffres", listOffre);
		 List<User> listUsers = userRepo.findAll();
		 model.addAttribute("listUsers", listUsers);  
		 List<Avatar> listAvatar = avatarRepo.findAll();
		 model.addAttribute("listAvatars", listAvatar);
		 List<Publication> listPubs = pubRepo.findLiveAuctions();
		 model.addAttribute("listPubs", listPubs);  
		 
		 Date startDate = pub.getDate_publication();
		 Date endDate = new Date(System.currentTimeMillis());
		 
		 long duration  = endDate.getTime() - startDate.getTime();

		 long diffInMinutes = TimeUnit.MILLISECONDS.toSeconds(duration);
		 
		 int diffMin=(int)diffInMinutes;  
		 System.out.println(diffInMinutes);
		 int countdown = pub.getDuration() - diffMin;
		 
		 if (countdown > 0) {
			 pub.setDuration(countdown)  ;
			} else {
				Offre offre = OffreRepo.findOne(Id_publication);
				if(Objects.nonNull(offre)) {
					pub.setOwnedby(offre.getUsername());
				}
				
					User user = userRepo.findByUsername(pub.getUsername());
					user.setFortune(user.getFortune() + pub.getPrix_init());
				  pub.setDuration(0);
			}
		 
		 Offre offre = OffreRepo.findOne(Id_publication);
		 if(Objects.nonNull(offre)) {
			 	
			  pub.setPrix_init(offre.getPrix());
			}
			
			 
			 
		pubRepo.save(pub);

	    model.addAttribute("pub", pub);
	    model.addAttribute("offre", new Offre());

	    return "postdetail";
	}
	@RequestMapping(value = "/postdetail/{id}", method = RequestMethod.GET)
	public String productDetail(@PathVariable("id") Integer Id_publication, Model model) {

		Publication pub = pubRepo.getById(Id_publication);
		List<Offre> listOffre = OffreRepo.findAllByPublicationid(Id_publication, Sort.by("prix").descending());
		model.addAttribute("listOffres", listOffre);
		 List<User> listUsers = userRepo.findAll();
		 model.addAttribute("listUsers", listUsers);  
		 List<Avatar> listAvatar = avatarRepo.findAll();
		 model.addAttribute("listAvatars", listAvatar);
		 List<Publication> listPubs = pubRepo.findLiveAuctions();
		 model.addAttribute("listPubs", listPubs);  
		 
		 
		 List<Commentaire> listCommentaire = commRepo.findAllByPublicationid(Id_publication);
			model.addAttribute("listCommentaires", listCommentaire);
			
			 model.addAttribute("comm", new Commentaire());
		 
		 Date startDate = pub.getDate_publication();
		 Date endDate = new Date(System.currentTimeMillis());
		 
		 long duration  = endDate.getTime() - startDate.getTime();

		 long diffInMinutes = TimeUnit.MILLISECONDS.toSeconds(duration);
		 
		 int diffMin=(int)diffInMinutes;  
		 System.out.println(diffInMinutes);
		 int countdown = pub.getDuration() - diffMin;
		 
		 if (countdown > 0) {
			 pub.setDuration(countdown)  ;
			} else {
				Offre offre = OffreRepo.findOne(Id_publication);
				if(Objects.nonNull(offre)) {
					pub.setOwnedby(offre.getUsername());
				}
				
					User user = userRepo.findByUsername(pub.getUsername());
					user.setFortune(user.getFortune() + pub.getPrix_init());
				  pub.setDuration(0);
			}
		 
		 Offre offre = OffreRepo.findOne(Id_publication);
		 if(Objects.nonNull(offre)) {
			 	
			  pub.setPrix_init(offre.getPrix());
			}
			
			 
			 
		pubRepo.save(pub);

	    model.addAttribute("pub", pub);
	    model.addAttribute("offre", new Offre());

	    return "postdetail";
	}
	@RequestMapping(value = "/userdetail/{username}", method = RequestMethod.GET)
	public String UserDetail(@PathVariable("username") String Username, Model model) {
		
		User user = userRepo.findByUsername(Username);
		List<Publication> Listpub = pubRepo.findByUsername(Username);
		 model.addAttribute("listPubs", Listpub);
		 List<User> listUsers = userRepo.findAll();
		 model.addAttribute("listUsers", listUsers); 
	    List<Avatar> listAvatar = avatarRepo.findAll();
		model.addAttribute("listAvatars", listAvatar);
	    model.addAttribute("user", user);
	   

	    return "userdetail";
	}
	@PostMapping("/pleeace_bid") 
	public String procsessAddItem(Offre offre, @RequestParam("participant") String user, @RequestParam("pubId") Integer pubID ) { 
		offre.setPublicationid(pubID);
		offre.setUsername(user);
		
		Offre savedOffre = OffreRepo.save(offre);

		return "redirect:postdetail/" + offre.getPublicationid();  }
	@PostMapping("/place_bid") 
	public String processAddItem(Offre offre, @RequestParam("participant") String user, @RequestParam("pubId") Integer pubID ) { 
		offre.setPublicationid(pubID);
		offre.setUsername(user);
		
		Publication pub = pubRepo.getById(offre.getPublicationid());
		if (offre.getPrix() > pub.getPrix_init()) {                                               
			Offre savedOffre = OffreRepo.save(offre);
			return "redirect:postdetail/" + offre.getPublicationid();
		}
		else {
			
		      return "redirect:postdetail/" + offre.getPublicationid();  
		}
		
		  }
	@PostMapping("/add_comm") 
	public String processAddItem(Commentaire comm, @RequestParam("participant") String user, @RequestParam("pubId") Integer pubID ) { 
		comm.setPublicationid(pubID);
		comm.setUsername(user);
		
		
		Commentaire savedCommentaire = commRepo.save(comm);

		return "redirect:postdetail/" + comm.getPublicationid();  }
	
	@PostMapping("/remove_comm")
	 public String processRemoveUser( @RequestParam("commentaire") Integer id_commentaire) {
		 Commentaire comm = commRepo.findById_commentaire(id_commentaire);
		 
	     commRepo.delete(comm);
	      
	     return "redirect:postdetail/" + comm.getPublicationid();  
	 }
	@GetMapping("/products")
	public String showProducts(Model model, @RequestParam Optional<String> titre, @RequestParam Optional<String> sort, @RequestParam Optional<Integer> page) {
		
		 List<User> listUsers = userRepo.findAll();
		 model.addAttribute("listUsers", listUsers); 
		 int currentPage = page.orElse(0);
		 Pageable paging = PageRequest.of(currentPage, 4, Sort.by(sort.orElse("titre")).descending());		 
		 Page<Publication> listPubs = pubRepo.findByTitre(titre.orElse("_"), paging);
		 model.addAttribute("listPubs", listPubs);
		 List<Avatar> listAvatar = avatarRepo.findAll();
		 model.addAttribute("listAvatars", listAvatar);
		 
		 int totalPages = listPubs.getTotalPages();
		 
		 model.addAttribute("currentPage", currentPage);
		 model.addAttribute("totalPages", totalPages);
		 
		 
		 return "products";
	 }
	
	
	@GetMapping("/products/sort/{id}")
	public String showProductsSorted(Model model, @PathVariable Integer id) {
		
		 String sort, disp;
		 switch(id) {
		 case 1: sort ="prix_init"; disp="Price"; break;
		 case 2: sort ="titre"; disp="Name"; break;
		 case 3: sort ="date" + (char)95 + "publication"; disp="Date"; break;
		 case 4: sort ="username"; disp="Owner"; break;
		 case 5: sort ="vendu"; disp="Availability"; break;
		 default: sort ="titre"; disp="Name";
		 }
		 List<User> listUsers = userRepo.findAll();
		 model.addAttribute("listUsers", listUsers);
		 List<Publication> listPubs = pubRepo.findAll(Sort.by(sort));
		 model.addAttribute("listPubs", listPubs);
		 model.addAttribute(disp);
		 List<Avatar> listAvatar = avatarRepo.findAll();
		 model.addAttribute("listAvatars", listAvatar);
		 
		 return "products";
	 }
	
	@GetMapping("/products/sort")
	public String redirectToSortPage(Model model) {
		return "redirect:sort/2";
	}
	
	@GetMapping("/products/sort/")
	public String redirectToSortPageV2(Model model) {
		return "redirect:2";
	}
	
	
	@GetMapping("/products/category/{id}")
	public String showProductsCategories(Model model, @PathVariable("id") Integer id) {
		
		 List<User> listUsers = userRepo.findAll();
		 model.addAttribute("listUsers", listUsers);
		 List<Publication> listPubs = pubRepo.findByCategorieid(id);
		 model.addAttribute("listPubs", listPubs);
		 List<Avatar> listAvatar = avatarRepo.findAll();
		 model.addAttribute("listAvatars", listAvatar);
		 
		 return "products";
	 }
	@PostMapping( "/send_request")
	public RedirectView sendrequest(@RequestParam("participant")  String Username, Model model) {
		
		Request req = RequestRepo.findByUsername(Username);
		
		if(Objects.isNull(req)) {
			Request  q = new Request();
			q.setUsername(Username);
			RequestRepo.save(q);
			
		}
		
	   

		return new RedirectView("/");
	}
	
	@GetMapping("/edit/profile")
	public String editProfile(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String authUser = auth.getName();
		User user = userRepo.findByUsername(authUser);
		model.addAttribute("user", user);
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);
		List<Avatar> listAvatar = avatarRepo.findAll();
		Avatar currentAvatar = avatarRepo.findById_avatar(user.getAvatarid());
		model.addAttribute("listAvatars", listAvatar);
		model.addAttribute("currentAvatar", currentAvatar);
		
		
		
		return "edit_profile";
	}
	
	
	@PostMapping("/edit/profile/success")
	public RedirectView updateUser(@ModelAttribute User user, @RequestParam("prenom") String prenom, @RequestParam("nom") String nom, @RequestParam("num_tele") String num_tele, @RequestParam("avatarid") Integer idAv) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String authUser = auth.getName();
		User connectedUser = userRepo.findByUsername(authUser);
		
		Avatar avatar = avatarRepo.findById_avatar(idAv);
		
		user.setUsername(connectedUser.getUsername());
		user.setEmail(connectedUser.getEmail());
		user.setFortune(connectedUser.getFortune());
		user.setMot_de_passe(connectedUser.getMot_de_passe());
		user.setIdCarte(connectedUser.getIdCarte());
		
		user.setNom(nom);
		user.setPrenom(prenom);
		user.setNum_tele(num_tele);
		user.setAvatar(avatar);
		
		userRepo.save(user);
		return new RedirectView("/userdetail/" + user.getUsername()); 
	}

	@GetMapping("/edit/post/{id}")
	public String editPost(Model model, @PathVariable("id") Integer id) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User connectedUser = userRepo.findByUsername(auth.getName());
		model.addAttribute("conn", connectedUser);
		
		List<Categorie> listCategorie = categorieRepo.findAll(); 
		model.addAttribute("listCategorie",listCategorie); 
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);
		List<Avatar> listAvatar = avatarRepo.findAll();
		model.addAttribute("listAvatars", listAvatar);
		
		Publication pub = pubRepo.findById_publication(id);
		model.addAttribute("pub", pub);
		
		Categorie cat = categorieRepo.findById_categorie(pub.getCategorieid());
		model.addAttribute("categorie", cat);
		System.out.println(pub);
		System.out.println(connectedUser);
		return "edit_post";
	}
	
	@PostMapping("/edit/post/success")
	public RedirectView updatePost(@ModelAttribute Publication pub, @RequestParam("titre") String titre, @RequestParam("description") String desc, @RequestParam("id_publication") Integer id) {
		
		Publication publication = pubRepo.findById_publication(id);
		publication.setTitre(titre);
		publication.setDescription(desc);
		
		pubRepo.save(publication);
		
		return new RedirectView("/postdetail/" + id);
	}
	@GetMapping("/creators")
	public String showCreators(Model model) {
		
		
		 List<User> listUsers = userRepo.findAll();		 
		 model.addAttribute("listUsers", listUsers);		
		 List<User> listSortedUsers = userRepo.findAndSortByFort();
		 model.addAttribute("sortedUsers", listSortedUsers);
		 List<Publication> listPubs = pubRepo.findAll();
		 model.addAttribute("listPubs", listPubs);  
		 List<Avatar> listAvatar = avatarRepo.findAll();
		 model.addAttribute("listAvatars", listAvatar);
		
		 Pageable paging = PageRequest.of(0, 1, Sort.by("vendu").ascending());
		 for(User user : listUsers) {
			 List<Publication> pubs = pubRepo.findTop4ByUsername(user.getUsername(), paging);
			 model.addAttribute("top4".concat(user.getUsername()), pubs);
		 }
		 
		 List<Publication> atop4 =  new ArrayList<>();
		 List<Long> pubCount =  new ArrayList<>();
		 for(User user2 : listUsers) {
			 atop4.addAll(pubRepo.findTop4ByUsername(user2.getUsername(), paging));
			 model.addAttribute("atop4", atop4);
			 pubCount.add(pubRepo.countPubPerUser(user2.getUsername()));
			 model.addAttribute("count", pubCount);
		 }
		 
		 
		
		
		return "creators";
	}
	
	
	@GetMapping("/help")
	public String helpPage(Model model) {
		
		
		
		
		
		return "help_center";
	}
	
	@GetMapping("/faq")
	public String faqPage(Model model) {
		return "faq";
	}
	
	
	@GetMapping("/contact")
	public String contactPage(Model model) {
		return "contact";
	}
	

	@GetMapping("/auctions")
	public String AuctionsPage(Model model) {

		 List<User> listUsers = userRepo.findAll();
		 model.addAttribute("listUsers", listUsers); 
		 List<Avatar> listAvatar = avatarRepo.findAll();
		 model.addAttribute("listAvatars", listAvatar);
		 List<Publication> listPubs = pubRepo.findLiveAuctions();
		 model.addAttribute("listPubs", listPubs);
		
		return "auctions";
	}
		
}
