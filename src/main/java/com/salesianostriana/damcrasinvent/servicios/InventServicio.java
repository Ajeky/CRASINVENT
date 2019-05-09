/**
 * 
 */
package com.salesianostriana.damcrasinvent.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.damcrasinvent.model.Invent;
import com.salesianostriana.damcrasinvent.repository.InventRepository;
import com.salesianostriana.damcrasinvent.servicios.base.BaseService;

/**
 * @author amarquez
 *
 */

@Service
public class InventServicio extends BaseService<Invent, Long, InventRepository>{

}
